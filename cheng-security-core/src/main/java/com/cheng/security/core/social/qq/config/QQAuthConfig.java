package com.cheng.security.core.social.qq.config;

import com.cheng.security.core.properties.QQProperties;
import com.cheng.security.core.properties.SecurityProperties;
import com.cheng.security.core.social.qq.connect.QQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.ConnectionFactory;

/**
 * @author cheng
 *         2018/8/8 20:39
 */
@Configuration
@ConditionalOnProperty(prefix = "cheng.security.social.qq", name = "app-id")
public class QQAuthConfig extends SocialAutoConfigurerAdapter {

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    protected ConnectionFactory<?> createConnectionFactory() {

        QQProperties qqConfig = securityProperties.getSocial().getQq();
        return new QQConnectionFactory(qqConfig.getProviderId(), qqConfig.getAppId(), qqConfig.getAppSecret());
    }
}