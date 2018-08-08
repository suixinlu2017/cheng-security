package com.cheng.security.core.properties;

import org.springframework.boot.autoconfigure.social.SocialProperties;

/**
 * QQ登录配置项
 *
 * @author cheng
 *         2018/8/8 20:34
 */
public class QQProperties extends SocialProperties {

    /**
     * 第三方 id，用来决定用户发起的第三方登录的 url，默认是 qq
     */
    private String providerId = "qq";

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }
}
