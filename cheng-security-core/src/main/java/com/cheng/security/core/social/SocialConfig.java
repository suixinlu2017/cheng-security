package com.cheng.security.core.social;

import com.cheng.security.core.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.social.security.SpringSocialConfigurer;

import javax.sql.DataSource;

/**
 * 社交配置适配器
 *
 * @author cheng
 *         2018/8/8 20:07
 */
@Configuration
@EnableSocial
public class SocialConfig extends SocialConfigurerAdapter {

    private static final String TABLE_PREFIX = "cheng_";

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private DataSource dataSource;

    @Autowired(required = false)
    private ConnectionSignUp connectionSignUp;

    @Autowired(required = false)
    private SocialAuthenticationFilterPostProcessor socialAuthenticationFilterPostProcessor;

    @Override
    public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {

        // 暂时不做加解密操作（noOpText）
        JdbcUsersConnectionRepository repository = new JdbcUsersConnectionRepository(dataSource,
                connectionFactoryLocator, Encryptors.noOpText());
        repository.setTablePrefix(TABLE_PREFIX);

        if (null != connectionSignUp) {
            repository.setConnectionSignUp(connectionSignUp);
        }

        return repository;
    }

    @Bean
    public SpringSocialConfigurer chengSocialSecurityConfig() {
        String filterProcessesUrl = securityProperties.getSocial().getFilterProcessesUrl();
        ChengSpringSocialConfigurer configurer = new ChengSpringSocialConfigurer(filterProcessesUrl);
        // 浏览器环境下注册跳转的地址
        configurer.signupUrl(securityProperties.getBrowser().getSignUpUrl());

        configurer.setSocialAuthenticationFilterPostProcessor(socialAuthenticationFilterPostProcessor);
        return configurer;
    }

    /**
     * 解决两个问题：
     * 1.在注册过程中拿到 Spring Social 的信息
     * 2.注册完成了如何把业务系统的用户id传给 Spring Social
     *
     * @param connectionFactoryLocator
     * @return
     */
    @Bean
    public ProviderSignInUtils providerSignInUtils(ConnectionFactoryLocator connectionFactoryLocator) {
        return new ProviderSignInUtils(connectionFactoryLocator,
                getUsersConnectionRepository(connectionFactoryLocator)) {
        };
    }
}
