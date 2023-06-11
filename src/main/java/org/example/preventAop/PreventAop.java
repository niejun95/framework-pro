package org.example.preventAop;

import com.alibaba.fastjson.JSON;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.concurrent.TimeUnit;

/**
 * @className PreventAop
 * @author ryan
 * @createTime 2022-10-10  11:17
 * @description 接口防刷 切面
 * @version 1.0
 */
@Aspect
@Component
public class PreventAop {
    public static final Logger log = LogManager.getLogger(PreventAop.class);

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Pointcut("@annotation(org.example.preventAop.Prevent)")
    public void pointcut() {
    }

    @Before("pointcut()")
    public void joinPoint(JoinPoint joinpoint) throws Exception {
        String requestStr = JSON.toJSONString(joinpoint.getArgs()[0]);
        if (StringUtils.isEmpty(requestStr) || requestStr.equalsIgnoreCase("{}")) {
            throw new RuntimeException("【防刷】入参不能为空");
        }

        MethodSignature methodSignature  = (MethodSignature) joinpoint.getSignature();
        Method method = joinpoint.getTarget().getClass().getMethod(methodSignature.getName(), methodSignature.getParameterTypes());

        Prevent preventAnnotation = method.getAnnotation(Prevent.class);
        String methodFullName = method.getDeclaringClass().getName() + method.getName();
        entrance(preventAnnotation, requestStr, methodFullName);
        return;
    }

    public void entrance(Prevent preventAnnotation, String requestStr, String methodFullName) throws Exception {
        PreventStrategy strategy = preventAnnotation.strategy();
        switch (strategy) {
            case DEFAULT:
                defaultHandler(requestStr, preventAnnotation, methodFullName);
                break;
            default:
                throw new RuntimeException("无效的策略");
        }

    }

    /**
     * 默认处理方法
     * @param requestStr
     * @param preventAnnotation
     * @param methodFullName
     */
    public void defaultHandler(String requestStr, Prevent preventAnnotation, String methodFullName) throws Exception {
        String base64Str = toBase64String(requestStr);
        long expire = Long.parseLong(preventAnnotation.value());

        String resp = redisTemplate.opsForValue().get(methodFullName + base64Str);
        if (StringUtils.isEmpty(resp)) {
            log.info("插入redis的key {}", methodFullName + base64Str);
            redisTemplate.opsForValue().set(methodFullName + base64Str, requestStr, expire, TimeUnit.SECONDS);
        } else {
            String message = !StringUtils.isEmpty(preventAnnotation.message()) ? preventAnnotation.message() :
                    expire + "秒内不允许重复请求";
            throw new RuntimeException(message);
        }
    }

    /* 对象转换为base64字符串
     * @param obj 对象值
     * @return base64字符串
     */
    public String toBase64String(String obj) throws Exception {
        if (StringUtils.isEmpty(obj)) {
            return null;
        }
        Base64.Encoder encoder = Base64.getEncoder();
        byte[] bytes = obj.getBytes(StandardCharsets.UTF_8);
        return encoder.encodeToString(bytes);
    }
}

