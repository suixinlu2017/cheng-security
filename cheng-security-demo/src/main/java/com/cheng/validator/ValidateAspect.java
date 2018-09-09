package com.cheng.validator;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.springframework.validation.BindingResult;

/**
 * @author cheng
 *         2018/9/9 16:11
 */
//@Aspect
//@Component
public class ValidateAspect {

    @Around("execution(* com.cheng.web.controller.UserController.*.(..))")
    public Object handlerValidateResult(ProceedingJoinPoint pjp)throws Throwable {

        System.out.println("进入切片");

        Object[] args = pjp.getArgs();
        for (Object arg : args) {
            if (arg instanceof BindingResult) {
                BindingResult errors = (BindingResult) arg;
                if (errors.hasErrors()) {
                    throw new ValidateException(errors.getAllErrors());
                }
            }
        }

        return pjp.proceed();
    }
}
