package com.cheng.web.config;

import com.cheng.web.filter.TimeFilter;
import com.cheng.web.interceptor.TimeInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * SpringMVC 配置注入 Bean
 *
 * @author cheng
 *         2018/8/4 21:03
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private TimeInterceptor timeInterceptor;

    @Bean
    public FilterRegistrationBean timeFilter() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();

        // 添加自定义 / 第三方过滤器
        TimeFilter timeFilter = new TimeFilter();
        registrationBean.setFilter(timeFilter);

        // 指定 Filter 在那些 url 中起作用
        List<String> url = new ArrayList<>();
        url.add("/*");
        registrationBean.setUrlPatterns(url);

        return registrationBean;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(timeInterceptor);
    }
}
