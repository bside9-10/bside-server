package com.bside.study.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Aspect
public class AppAspect {

    @Around("execution(* com.bside.study.goal.service..*.*(..)) || execution(* com.bside.study.user.service..*.*(..))")
    public Object serviceLogging(ProceedingJoinPoint jp) throws Exception {
        try {
            Object obj = jp.proceed();

            log.info(String.format("%s", jp.getSignature().toShortString()));

            return obj;
        } catch (Throwable e) {
            throw new Exception();
        }
    }

}
