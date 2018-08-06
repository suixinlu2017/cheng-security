package com.cheng.security.core.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 安全环境配置项
 *
 * @author cheng
 *         2018/8/6 16:01
 */
@ConfigurationProperties(prefix = "cheng.security")
public class SecurityProperties {

    private BrowserProperties browser = new BrowserProperties();

    public BrowserProperties getBrowser() {
        return browser;
    }

    public void setBrowser(BrowserProperties browser) {
        this.browser = browser;
    }
}
