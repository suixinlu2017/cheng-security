package com.cheng.security.core.authorize;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;

/**
 * 授权信息管理器
 * <p>
 * 用于收集系统中所有 AuthorizeConfigProvider 并加载其配置
 *
 * @author cheng
 *         2018/9/8 14:30
 */
public interface AuthorizeConfigManager {

    /**
     * 授权配置
     *
     * @param config
     */
    void config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config);
}
