package org.example.annotations;

import cn.hutool.crypto.digest.MD5;
import cn.hutool.http.HttpStatus;
import com.alibaba.fastjson2.JSON;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.example.constants.CacheConstants;
import org.example.entity.CommonResult;
import org.example.utils.ServletUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.time.Duration;


/**
 * @author ryan
 * @version 1.0.0
 * @description 防止重复提交（参考美团 GTIS 防重系统）
 * @date 2023/07/01 21:52:04
 */
@Aspect
@Component
public class RepeatSubmitAspect {

    private static final ThreadLocal<String> KEY_CACHE = new ThreadLocal<>();

    @Value("${token.header}")
    private String header;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Before("@annotation(repeatSubmit)")
    public void doBefore(JoinPoint point, RepeatSubmit repeatSubmit) {
        // 如果注解不为0 则使用注解数值
        long interval = 0;
        if (repeatSubmit.interval() > 0) {
            interval = repeatSubmit.timeUnit().toMillis(repeatSubmit.interval());
        }
        // 注解中配置的事件必须大于一秒，也就是只要要给一秒以上的时间，否则直接报异常
        if (interval < 1000) {
            throw new RuntimeException("重复提交间隔时间不能小于'1'秒");
        }
        HttpServletRequest request = ServletUtils.getRequest();
        String nowParams = JSON.toJSONString(point.getArgs());

        // 请求地址（作为存放 cache 的 key 值）
        String url = request.getRequestURI();
        // 唯一值（没有消息头则使用请求地址）
        String submitKey = trimToEmpty(request.getHeader(header));
        // submitKye to md5
        submitKey = MD5.create().digestHex(submitKey + ":" + nowParams);

        // 唯一标识（指定key + url + 消息头）
        String cacheRepeatKey = CacheConstants.GLOBAL_REPEAT_SUBMIT_KEY + url + submitKey;
        String key = redisTemplate.opsForValue().get(cacheRepeatKey);
        if (key == null) {
            redisTemplate.opsForValue().set(cacheRepeatKey, "", Duration.ofMillis(interval));
            KEY_CACHE.set(cacheRepeatKey);
        } else {
            String message = repeatSubmit.message();
            if (message.startsWith("{") && message.endsWith("}")) {
                message = message.substring(1, message.length() - 1);
            }
            throw new RuntimeException(message);
        }
    }

    @AfterReturning(pointcut = "@annotation(repeatSubmit)", returning = "jsonResult")
    public void doAfterReturning(JoinPoint joinPoint, RepeatSubmit repeatSubmit, Object jsonResult) {
        try {
            if (jsonResult instanceof CommonResult) {
                CommonResult r = (CommonResult) jsonResult;
                // 成功则不删除 redis 数据 保证在有效时间内无法重复提交
                if (r.getCode() == HttpStatus.HTTP_OK) {
                    return;
                } else {
                    // 抛出了异常 被 GlobalExceptionHandler 进行封装了 需要删除缓存
                    redisTemplate.delete(KEY_CACHE.get());
                }
            }
        } finally {
            KEY_CACHE.remove();
        }
    }

    @AfterThrowing(pointcut = "@annotation(RepeatSubmit)", throwing = "ex")
    public void doAfterThrowing(Throwable ex) {
        // 执行失败
        redisTemplate.delete(KEY_CACHE.get());
        KEY_CACHE.remove();
    }

    private String trimToEmpty(String str) {
        return str == null ? "" : str.trim();
    }
}
