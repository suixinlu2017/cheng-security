package com.cheng.web.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.Instant;

/**
 * Spring 拦截器
 *
 * @author cheng
 *         2018/8/4 21:10
 */
@Component
public class TimeInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        System.out.println("preHandle");

        System.out.println(((HandlerMethod) handler).getBean().getClass().getName());
        System.out.println(((HandlerMethod) handler).getMethod().getName());
        request.setAttribute("startTime", Instant.now().toEpochMilli());

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
        System.out.println("postHandle");

        Long startTime = (Long) request.getAttribute("startTime");
        long endTime = Instant.now().toEpochMilli();
        System.out.println("time interceptor 耗时: " + (endTime - startTime) + "ms");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        System.out.println("afterCompletion");

        Long startTime = (Long) request.getAttribute("startTime");
        long endTime = Instant.now().toEpochMilli();
        System.out.println("time interceptor 耗时: " + (endTime - startTime) + "ms");
        System.out.println("exception is " + ex);
    }
}
