package com.cheng.security.core.properties;

/**
 * @author cheng
 *         2018/08/31 10:00
 */
public class OAuth2Properties {

    /**
     * 使用 jwt 时为 token 签名的密钥
     */
    private String jwtSigningKey = "cheng";

    /**
     * 客户端配置
     */
    private OAuth2ClientProperties[] clients = {};

    public String getJwtSigningKey() {
        return jwtSigningKey;
    }

    public void setJwtSigningKey(String jwtSigningKey) {
        this.jwtSigningKey = jwtSigningKey;
    }

    public OAuth2ClientProperties[] getClients() {
        return clients;
    }

    public void setClients(OAuth2ClientProperties[] clients) {
        this.clients = clients;
    }
}
