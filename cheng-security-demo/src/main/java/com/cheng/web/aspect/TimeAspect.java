package com.cheng.web.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;

import java.time.Instant;

/**
 * @author cheng
 *         2018/8/4 21:46
 */
//@Aspect
//@Component
public class TimeAspect {

    @Around("execution(* com.cheng.web.controller .UserController.*(..))")
    public Object handlerControllerMethod(ProceedingJoinPoint pjp) throws Throwable {

        System.out.println("time aspect start");
        Object[] args = pjp.getArgs();
        for (Object arg : args) {
            System.out.println("arg is " + arg);
        }

        long startTime = Instant.now().toEpochMilli();
        Object object = pjp.proceed();
        long endTimeTime = Instant.now().toEpochMilli();

        System.out.println("time aspect 耗时: " + (startTime - endTimeTime) + "ms");
        System.out.println("time aspect end");

        return object;
    }
}
