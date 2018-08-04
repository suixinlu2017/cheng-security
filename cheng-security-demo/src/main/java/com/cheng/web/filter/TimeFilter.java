package com.cheng.web.filter;

import javax.servlet.*;
import java.io.IOException;
import java.time.Instant;

/**
 * javax 过滤器
 *
 * @author cheng
 *         2018/8/4 20:52
 */
public class TimeFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) {
        System.out.println("time filter init");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("my filter start");

        long startTime = Instant.now().toEpochMilli();

        chain.doFilter(request, response);
        long endTime = Instant.now().toEpochMilli();
        System.out.println("time filter 耗时: " + (endTime - startTime) + "ms");

        System.out.println("time filter finish");
    }

    @Override
    public void destroy() {
        System.out.println("time filter destroy");
    }
}
