package com.cheng.security.app;

import com.cheng.security.app.social.openid.OpenIdAuthenticationSecurityConfig;
import com.cheng.security.core.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import com.cheng.security.core.properties.SecurityConstants;
import com.cheng.security.core.properties.SecurityProperties;
import com.cheng.security.core.validate.code.ValidateCodeSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.social.security.SpringSocialConfigurer;

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
    private SecurityProperties securityProperties;

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

    @Override
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
                .authorizeRequests()
                .antMatchers(
                        SecurityConstants.DEFAULT_AUTHENTICATION_URL,
                        SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_MOBILE,
                        SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_OPENID,
                        securityProperties.getBrowser().getLoginPage(),
                        SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX + "/*",
                        securityProperties.getBrowser().getSignUpUrl(),
                        securityProperties.getBrowser().getSignOutUrl(),
                        securityProperties.getBrowser().getSession().getSessionInvalidUrl(),
                        "/user/register", "/social/singUp")
                .permitAll()
                .anyRequest()
                .authenticated()

                .and()
                .csrf().disable();
    }
}
