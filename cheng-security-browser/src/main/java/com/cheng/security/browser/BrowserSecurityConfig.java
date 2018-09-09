package com.cheng.security.browser;

import com.cheng.security.core.authentication.FormAuthenticationConfig;
import com.cheng.security.core.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import com.cheng.security.core.authorize.AuthorizeConfigManager;
import com.cheng.security.core.properties.SecurityProperties;
import com.cheng.security.core.validate.code.ValidateCodeSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.social.security.SpringSocialConfigurer;

import javax.sql.DataSource;

/**
 * 浏览器环境下安全配置主类
 *
 * @author cheng
 *         2018/8/6 13:33
 */
@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;

    @Autowired
    private ValidateCodeSecurityConfig validateCodeSecurityConfig;

    @Autowired
    private SpringSocialConfigurer chengSpringSocialConfigurer;

    @Autowired
    private SessionInformationExpiredStrategy sessionInformationExpiredStrategy;

    @Autowired
    private InvalidSessionStrategy invalidSessionStrategy;

    @Autowired
    private LogoutSuccessHandler logoutSuccessHandler;

    @Autowired
    private AuthorizeConfigManager authorizeConfigManager;

    @Autowired
    private FormAuthenticationConfig formAuthenticationConfig;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        formAuthenticationConfig.configure(http);

        http
                // 校验码相关配置
                .apply(validateCodeSecurityConfig)

                .and()
                // 短信登录相关配置
                .apply(smsCodeAuthenticationSecurityConfig)

                .and()
                // 社交登录配置
                .apply(chengSpringSocialConfigurer)

                .and()
                // 记住我功能
                .rememberMe()
                .tokenRepository(persistentTokenRepository())
                .tokenValiditySeconds(securityProperties.getBrowser().getRememberMeSeconds())
                .userDetailsService(userDetailsService)

                .and()
                // session 超时处理
                .sessionManagement()
                // session 过期处理
                .invalidSessionStrategy(invalidSessionStrategy)
                // 最大 session 数量
                .maximumSessions(securityProperties.getBrowser().getSession().getMaximumSession())
                // 当session数量达到当前最大值，阻止用户登录行为
                .maxSessionsPreventsLogin(securityProperties.getBrowser().getSession().isMaxSessionPreventsLogin())
                // 并发登录导致 session 超时的处理策略
                .expiredSessionStrategy(sessionInformationExpiredStrategy)
                .and()

                .and()
                .logout()
                // 退出登录请求
                .logoutUrl("/signOut")
                // 退出成功请求
                .logoutSuccessHandler(logoutSuccessHandler)
                // 退出成功时删除 cookie 信息
                .deleteCookies("JSESSIONID")

                .and()
                .csrf().disable();

        // 针对url的安全配置
        authorizeConfigManager.config(http.authorizeRequests());
    }

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
}
