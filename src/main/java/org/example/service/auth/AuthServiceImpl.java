package org.example.service.auth;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.example.constants.AuthConstants;
import org.example.entity.Account;
import org.example.entity.RememberMeToken;
import org.example.mapper.AccountMapper;
import org.example.mapper.RememberTokenMapper;
import org.example.utils.CookieUtil;
import org.example.utils.TokenManager;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @description 登录功能实现类
 * @author ryan
 * @date 2023/4/25 9:33
 * @version 1.0
 */
@Service
@Slf4j
public class AuthServiceImpl implements AuthService {

    @Resource
    AccountMapper accountMapper;

    @Resource
    RedisTemplate redisTemplate;

    @Resource
    TokenManager tokenManager;

    @Resource
    RememberTokenMapper rememberTokenMapper;

    private static final Random random = new SecureRandom();

    public static final String COOKIE_NAME = "remember-me";

    private static final String PHONE_INTERVAL_FLAG = "1";

    @Override
    public void sendSmsCaptcha(String phone) {
        Account account = accountMapper.queryByPhone(phone);
        if (account == null) {
            throw new RuntimeException("手机号不在白名单内！");
        }
        // 验证码发送时间 间隔判断 是否超过1分钟
        String phoneSmsIntervalKey = AuthConstants.SMS_CODE_INTERVAL + phone;
        String phoneSmsIntervalFlag = (String) redisTemplate.opsForValue().get(phoneSmsIntervalKey);
        if (PHONE_INTERVAL_FLAG.equals(phoneSmsIntervalFlag)) {
            log.error("手机号[{}]短信验证码发送间隔未到1分钟", phone);
            throw new RuntimeException("手机号[" + phone + "]短信验证码发送间隔未到1分钟");
        }

        redisTemplate.opsForValue().set(phoneSmsIntervalKey, PHONE_INTERVAL_FLAG, AuthConstants.SMS_CODE_EXPIRE_TIME * 60, TimeUnit.SECONDS);

        String captcha = getRandomCode();
        log.info("生成的验证码为：{}", captcha);
        // TODO: 发送短信验证码到指定手机号上

        // 存储短信验证码存储
        String smsCaptchaKey = AuthConstants.SMS_CODE_VALUE_PREFIX + phone;
        redisTemplate.opsForValue().set(smsCaptchaKey, captcha, AuthConstants.SMS_CODE_EXPIRE_TIME * 60, TimeUnit.SECONDS);

        // 短信验证码 验证次数
        String captchaCheckTimeKey = AuthConstants.SMS_CODE_CHECK_TIME_PREFIX + phone;
        redisTemplate.opsForValue().set(captchaCheckTimeKey, "0", AuthConstants.SMS_CODE_EXPIRE_TIME * 60, TimeUnit.SECONDS);
    }

    @Override
    public Account verifySmsCaptcha(String phone, String smsCaptcha) {
        if (StrUtil.isEmpty(phone) || StrUtil.isEmpty(smsCaptcha)) {
            log.error("手机号和验证码不能为空");
            throw new RuntimeException("手机号和验证码不能为空");
        }
        String captchaCheckTimeKey = AuthConstants.SMS_CODE_CHECK_TIME_PREFIX + phone;
        String captchaCheckTimeValue = (String) redisTemplate.opsForValue().get(captchaCheckTimeKey);
        if (StrUtil.isEmpty(captchaCheckTimeValue)) {
            log.error("验证码过期");
            throw new RuntimeException("验证码已过期");
        }
        Integer captchaCheckTime = Integer.valueOf(captchaCheckTimeValue);
        if (captchaCheckTime >= AuthConstants.SMS_CODE_CHECK_TIME) {
            log.error("验证码验证次数超过最大次数");
            throw new RuntimeException("验证码验证次数超过最大次数");
        }
        redisTemplate.opsForValue().set(captchaCheckTimeKey, captchaCheckTime++, AuthConstants.SMS_CODE_EXPIRE_TIME * 60, TimeUnit.SECONDS);


        // 验证验证码是否正确
        String smsCaptchaKey = AuthConstants.SMS_CODE_VALUE_PREFIX + phone;
        String backSmsCaptcha = (String) redisTemplate.opsForValue().get(smsCaptchaKey);
        if (StrUtil.isNotEmpty(backSmsCaptcha) && backSmsCaptcha.equals(smsCaptcha)) {
            // 验证通过 删除短信验证码
            redisTemplate.delete(smsCaptchaKey);
            return accountMapper.queryByPhone(phone);
        } else {
            throw new RuntimeException("验证码输入错误或验证码已失效");
        }
    }

    @Override
    public void logout(HttpServletRequest request) {
        String token = request.getHeader("token");
        if (StrUtil.isEmpty(token)) {
            return;
        } else {
            redisTemplate.delete(AuthConstants.LOGIN_TOKEN + token);

            // TODO：删除记住登录信息

        }
    }

    @Override
    public void rememberMeLogin(String phone, String frontSmsCaptcha, HttpServletResponse response) {
        Account account = verifySmsCaptcha(phone, frontSmsCaptcha);

        // 删除旧的信息
        QueryWrapper<RememberMeToken> wrapper = new QueryWrapper<>();
        wrapper.eq("phone", phone);
        rememberTokenMapper.delete(wrapper);

        // 新增新的
        RememberMeToken rememberMeToken = new RememberMeToken(phone, generateRandomData(), generateRandomData(), new Date());
        rememberTokenMapper.insert(rememberMeToken);
        String[] tokens = {rememberMeToken.getSeries(), rememberMeToken.getToken()};
        setCookie(tokens, response);
    }

    @Override
    public String simpleLogin(String phone, String frontSmsCaptcha) {
        try {
            Account account = verifySmsCaptcha(phone, frontSmsCaptcha);
            String token = tokenManager.generateToken(account);
            log.info("生成的token为：[{}]", token);
            redisTemplate.opsForValue().set(AuthConstants.LOGIN_TOKEN + token, account, AuthConstants.TOKEN_EXPIRE_TIME * 60, TimeUnit.SECONDS);
            return token;
        } catch (Exception e) {
            log.error("生成token异常：{}", e.getMessage(), e);
            throw new RuntimeException("生成token异常");
        }
    }

    private String getRandomCode() {
        int length = 6;
        StringBuilder builder = new StringBuilder(6);
        for (int i = 0; i < length; i++) {
            long random = Math.round(Math.random() * 9);
            builder.append(random);
        }
        return builder.toString();
    }

    private String generateRandomData() {
        byte[] newSeries = new byte[16];
        random.nextBytes(newSeries);
        return new String(Base64.getEncoder().encode(newSeries));
    }

    private void setCookie(String[] tokens, HttpServletResponse response) {
        String cookieValue = CookieUtil.encodeCookie(tokens);
        Cookie cookie = new Cookie(COOKIE_NAME, cookieValue);
        log.info("cooke = [{}]", JSON.toJSONString(cookie));
        response.addCookie(cookie);
    }
}
