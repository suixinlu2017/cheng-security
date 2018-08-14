package com.cheng.security.core.properties;

import org.springframework.boot.autoconfigure.social.SocialProperties;

/**
 * 微信登录配置项
 *
 * @author cheng
 *         2018/8/13 19:47
 */
public class WechatProperties extends SocialProperties {

    /**
     * 第三方 id，用来决定发起第三方登录的 url，默认是 Wechat
     */
    private String providerId = "wechat";

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }
}
