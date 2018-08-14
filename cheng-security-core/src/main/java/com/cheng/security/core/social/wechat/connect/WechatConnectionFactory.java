package com.cheng.security.core.social.wechat.connect;

import com.cheng.security.core.social.wechat.api.Wechat;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionData;
import org.springframework.social.connect.support.OAuth2Connection;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2ServiceProvider;

/**
 * 微信连接工厂
 *
 * @author cheng
 *         2018/8/13 21:17
 */
public class WechatConnectionFactory extends OAuth2ConnectionFactory<Wechat> {

    public WechatConnectionFactory(String providerId, String appId, String appSecret) {
        super(providerId, new WechatServiceProvider(appId, appSecret), new WechatAdapter());
    }

    /**
     * 由于微信的 openId 是和 accessToken 是一起返回的，
     * 所以在这里直接根据 accessToken 设置 providerUserId 即可，不用像QQ那样通过 QQAdapter 获取
     *
     * @param accessGrant
     * @return
     */
    @Override
    protected String extractProviderUserId(AccessGrant accessGrant) {
        if (accessGrant instanceof WechatAccessGrant) {
            return ((WechatAccessGrant) accessGrant).getOpenId();
        }

        return null;
    }

    @Override
    public Connection<Wechat> createConnection(AccessGrant accessGrant) {
        return new OAuth2Connection<>(getProviderId(), extractProviderUserId(accessGrant),
                accessGrant.getAccessToken(), accessGrant.getRefreshToken(), accessGrant.getExpireTime(), getOAuth2ServiceProvider(), getApiAdapter(extractProviderUserId(accessGrant)));
    }

    @Override
    public Connection<Wechat> createConnection(ConnectionData data) {
        return new OAuth2Connection<>(data, getOAuth2ServiceProvider(), getApiAdapter(data.getProviderUserId()));
    }

    private ApiAdapter<Wechat> getApiAdapter(String providerUserId) {
        return new WechatAdapter(providerUserId);
    }

    private OAuth2ServiceProvider<Wechat> getOAuth2ServiceProvider() {
        return (OAuth2ServiceProvider<Wechat>) getServiceProvider();
    }
}
