package com.cheng.security.core.properties;

/**
 * 认证服务器注册的第三方应用配置项
 *
 * @author cheng
 *         2018/08/31 10:00
 */
public class OAuth2ClientProperties {

    /**
     * 第三方应用 appId
     */
    private String clientId;

    /**
     * 第三方应用 appSecret
     */
    private String clientSecret;

    /**
     * 针对此应用发出的 token 的有效时间
     */
    private int accessTokenValidateSeconds = 7200;

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public int getAccessTokenValidateSeconds() {
        return accessTokenValidateSeconds;
    }

    public void setAccessTokenValidateSeconds(int accessTokenValidateSeconds) {
        this.accessTokenValidateSeconds = accessTokenValidateSeconds;
    }
}
