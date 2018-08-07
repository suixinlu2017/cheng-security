package com.cheng.security.browser;

import com.cheng.security.core.properties.SecurityProperties;
import com.cheng.security.core.validate.code.ValidateCodeFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

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

    @Autowired
    private DataSource dataSource;

    @Autowired
    private UserDetailsService userDetailsService;

    /**
     * 记住我功能
     *
     * @return
     */
    @Bean
    public PersistentTokenRepository persistentTokenRepository() {

        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        // 数据源
        tokenRepository.setDataSource(dataSource);
        // 创建表
//        tokenRepository.setCreateTableOnStartup(true);
        return tokenRepository;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        ValidateCodeFilter validateCodeFilter = new ValidateCodeFilter();
        validateCodeFilter.setAuthenticationFailureHandler(chengAuthenticationFailureHandler);

        validateCodeFilter.setSecurityProperties(securityProperties);
        validateCodeFilter.afterPropertiesSet();


        http
                // 验证码验证
                .addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class)
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
                // 记住我功能
                .rememberMe()
                // 数据源
                .tokenRepository(persistentTokenRepository())
                // 过期时间
                .tokenValiditySeconds(securityProperties.getBrowser().getRememberMeSeconds())
                // 拿到用户名密码 登录
                .userDetailsService(userDetailsService)

                .and()
                // 请求授权
                .authorizeRequests()
                // 配置该链接不需要认证
                .antMatchers("/authentication/require",
                        securityProperties.getBrowser().getLoginPage(),
                        "/code/*").permitAll()
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
