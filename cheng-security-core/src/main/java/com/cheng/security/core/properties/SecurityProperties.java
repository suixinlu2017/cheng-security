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

    /**
     * 浏览器环境配置
     */
    private BrowserProperties browser = new BrowserProperties();

    /**
     * 验证码配置
     */
    private ValidateCodeProperties code = new ValidateCodeProperties();

    public BrowserProperties getBrowser() {
        return browser;
    }

    public void setBrowser(BrowserProperties browser) {
        this.browser = browser;
    }

    public ValidateCodeProperties getCode() {
        return code;
    }

    public void setCode(ValidateCodeProperties code) {
        this.code = code;
    }
}
