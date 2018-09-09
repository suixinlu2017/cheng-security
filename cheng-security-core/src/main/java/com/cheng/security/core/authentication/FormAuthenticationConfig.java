package com.cheng.security.core.authentication;

import com.cheng.security.core.properties.SecurityConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

/**
 * 表单登录配置
 *
 * @author cheng
 *         2018/9/9 12:39
 */
@Component
public class FormAuthenticationConfig {

    @Autowired
    private AuthenticationSuccessHandler chengAuthenticationSuccessHandler;

    @Autowired
    private AuthenticationFailureHandler chengAuthenticationFailureHandler;

    public void configure(HttpSecurity http) throws Exception {

        http
                .formLogin()
                // 登录页
                .loginPage(SecurityConstants.DEFAULT_AUTHENTICATION_URL)
                // 登录处理页
                .loginProcessingUrl(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM)
                // 成功 handler
                .successHandler(chengAuthenticationSuccessHandler)
                // 失败 handler
                .failureHandler(chengAuthenticationFailureHandler);
    }
}
