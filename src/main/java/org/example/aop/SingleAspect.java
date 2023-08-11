package org.example.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class SingleAspect {


    @Pointcut("execution(* org.example.service.aop.AopService.test(..))")
    public void point() {
    }


    @Around(value = "point()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        log.info("aspect before log");
        Object[] args = pjp.getArgs();
        String method = pjp.getSignature().getName();
        Object target = pjp.getTarget();
        Object result = pjp.proceed();
        log.info("方法名： {}， 目标对象： {}， 返回值： {}", method, target, result);
        log.info("aspect after log");
        return result;
    }
}
