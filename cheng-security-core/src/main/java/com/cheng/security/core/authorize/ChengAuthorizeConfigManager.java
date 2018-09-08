package com.cheng.security.core.authorize;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * 默认的授权配置管理器
 *
 * @author cheng
 *         2018/9/8 14:33
 */
@Component
public class ChengAuthorizeConfigManager implements AuthorizeConfigManager {

    @Autowired
    private Set<AuthorizeConfigProvider> authorizeConfigProviders;

    @Override
    public void config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {

        for (AuthorizeConfigProvider authorizeConfigProvider : authorizeConfigProviders) {
            authorizeConfigProvider.config(config);
        }

        // 除了上面配置的权限请求，剩下的所有请求都需要身份认证才能访问
        config.anyRequest().authenticated();
    }
}
