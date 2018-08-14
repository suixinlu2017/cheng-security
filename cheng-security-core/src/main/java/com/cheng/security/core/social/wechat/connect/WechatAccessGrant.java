package com.cheng.security.core.social.wechat.connect;

import org.springframework.social.oauth2.AccessGrant;

/**
 * 微信的 access_token 信息，与标准的 OAuth2 协议不同，微信在获取 access_token 时会同时返回 openId，并没有单独的通过 accessToken 获取 openId 的服务
 * 所以在这里继承了标准 AccessGrant，添加了 openId 字段，作为对微信 access_token 信息的封装
 *
 * @author cheng
 *         2018/8/13 20:36
 */
public class WechatAccessGrant extends AccessGrant {

    private static final long serialVersionUID = 1993719581506704591L;

    private String openId;

    public WechatAccessGrant() {
        super("");
    }


    public WechatAccessGrant(String accessToken, String scope, String refreshToken, Long expiresIn) {
        super(accessToken, scope, refreshToken, expiresIn);
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }
}
