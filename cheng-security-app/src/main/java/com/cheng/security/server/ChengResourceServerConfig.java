package com.cheng.security.server;

import com.cheng.security.app.authentication.openid.OpenIdAuthenticationSecurityConfig;
import com.cheng.security.core.authentication.FormAuthenticationConfig;
import com.cheng.security.core.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import com.cheng.security.core.authorize.AuthorizeConfigManager;
import com.cheng.security.core.validate.code.ValidateCodeSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.social.security.SpringSocialConfigurer;

import javax.sql.DataSource;

/**
 * 资源服务器配置
 *
 * @author cheng
 *         2018/8/26 12:48
 */
@Configuration
@EnableResourceServer
public class ChengResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;

    @Autowired
    private OpenIdAuthenticationSecurityConfig openIdAuthenticationSecurityConfig;

    @Autowired
    private ValidateCodeSecurityConfig validateCodeSecurityConfig;

    @Autowired
    private SpringSocialConfigurer chengSpringSocialConfig;

    @Autowired
    private AuthenticationSuccessHandler chengAuthenticationSuccessHandler;

    @Autowired
    private AuthenticationFailureHandler chengAuthenticationFailureHandler;

    @Autowired
    private AuthorizeConfigManager authorizeConfigManager;

    @Autowired
    private FormAuthenticationConfig formAuthenticationConfig;

    @Override
    public void configure(HttpSecurity http) throws Exception {

        formAuthenticationConfig.configure(http);

        http
                // 校验码相关配置
                .apply(validateCodeSecurityConfig)

                .and()
                // 短信登录相关配置
                .apply(smsCodeAuthenticationSecurityConfig)

                .and()
                // 社交登录配置
                .apply(chengSpringSocialConfig)

                .and()
                // app 登录 openid 配置
                .apply(openIdAuthenticationSecurityConfig)

                .and()
                .csrf().disable();

        // 针对url的安全配置
        authorizeConfigManager.config(http.authorizeRequests());
    }
}
