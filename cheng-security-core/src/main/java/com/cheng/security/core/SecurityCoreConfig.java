package com.cheng.security.core;

import com.cheng.security.core.properties.SecurityProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 注入 SecurityProperties 配置
 *
 * @author cheng
 *         2018/8/6 16:03
 */
@Configuration
@EnableConfigurationProperties(SecurityProperties.class)
public class SecurityCoreConfig {
}
