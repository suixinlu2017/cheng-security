package com.cheng.security.browser;

import com.cheng.security.core.properties.SecurityProperties;
import com.cheng.security.core.validate.code.ValidateCodeFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 * Spring Security 授权组件
 *
 * @author cheng
 *         2018/8/6 13:33
 */
@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private AuthenticationSuccessHandler chengAuthenticationSuccessHandler;

    @Autowired
    private AuthenticationFailureHandler chengAuthenticationFailureHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        ValidateCodeFilter validateCodeFilter = new ValidateCodeFilter();
        validateCodeFilter.setAuthenticationFailureHandler(chengAuthenticationFailureHandler);


        http
                // form表单验证
                .formLogin()
                // 自定义登录页面
                .loginPage("/authentication/require")
                // 身份验证请求 url
                .loginProcessingUrl("/authentication/form")
                // 表单登录成功处理
                .successHandler(chengAuthenticationSuccessHandler)
                // 表单登录失败处理
                .failureHandler(chengAuthenticationFailureHandler)
                .and()
                // 请求授权
                .authorizeRequests()
                // 配置该链接不需要认证
                .antMatchers("/authentication/require",
                        securityProperties.getBrowser().getLoginPage(),
                        "/code/image").permitAll()
                // 任何请求
                .anyRequest()
                // 需要身份认证
                .authenticated()
                // 暂时配置跨域请求无效
                .and().csrf().disable();
    }

    /**
     * 密码编码器
     *
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
