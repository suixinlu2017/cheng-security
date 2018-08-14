package com.cheng.security.core.social.wechat.connect;

import com.cheng.security.core.social.wechat.api.Wechat;
import com.cheng.security.core.social.wechat.api.WechatImpl;
import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;

/**
 * 微信的 OAuth2 流程处理器的提供器，供 Spring social 的 connect 体系调用
 *
 * @author cheng
 *         2018/8/13 21:13
 */
public class WechatServiceProvider extends AbstractOAuth2ServiceProvider<Wechat> {

    /**
     * 微信获取授权码的 url
     */
    private static final String URL_AUTHORIZE = "https://open.weixin.qq.com/connect/qrconnect";

    /**
     * 微信获取accessToken的url
     */
    private static final String URL_ACCESS_TOKEN = "https://api.weixin.qq.com/sns/oauth2/access_token";


    public WechatServiceProvider(String appId, String appSecret) {
        super(new WechatOAuth2Template(appId, appSecret, URL_AUTHORIZE, URL_ACCESS_TOKEN));
    }

    @Override
    public Wechat getApi(String accessToken) {
        return new WechatImpl(accessToken);
    }
}
