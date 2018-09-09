package com.cheng.security.core.authentication;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.security.SocialUserDetailsService;

/**
 * 认证相关的扩展点配置
 * <p>
 * 配置在这里的 bean，业务系统都可以通过声明同类型或同名的 bean 来覆盖安全模块的配置
 *
 * @author cheng
 *         2018/9/9 16:30
 */
@Configuration
public class AuthenticationBeanConfig {

    /**
     * 默认密码处理器
     *
     * @return
     */
    @Bean
    @ConditionalOnMissingBean(PasswordEncoder.class)
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 默认认证器
     *
     * @return
     */
    @Bean
    @ConditionalOnMissingBean(UserDetailsService.class)
    public UserDetailsService userDetailsService() {
        return new DefaultUserDetailsServiceImpl();
    }

    /**
     * 默认认证器
     *
     * @return
     */
    public SocialUserDetailsService socialUserDetailsService() {
        return new DefaultSocialUserDetailsServiceImpl();
    }
}
