package com.cheng.security.core.social.wechat.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

/**
 * Wechat API 调用模版， scope 为 Request 的 Spring bean，根据当前用户的 accessToken 创建
 *
 * @author cheng
 *         2018/8/13 20:17
 */
public class WechatImpl extends AbstractOAuth2ApiBinding implements Wechat {

    private ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 获取用户信息的 url
     */
    private static final String URL_GET_USER_INFO = "https://api.weixin.qq.com/sns/userinfo?openid=";

    public WechatImpl(String accessToken) {
        super(accessToken, TokenStrategy.ACCESS_TOKEN_PARAMETER);
    }

    /**
     * 默认注册 StringHttpMessageConverter 字符集为 ISO-8859-1，而微信返回的是UTF-8，所以覆盖了原来的方法
     *
     * @return
     */
    @Override
    protected List<HttpMessageConverter<?>> getMessageConverters() {

        List<HttpMessageConverter<?>> messageConverters = super.getMessageConverters();
        messageConverters.remove(0);
        messageConverters.add(new StringHttpMessageConverter(Charset.forName("UTF-8")));
        return messageConverters;
    }

    /**
     * 获取微信用户信息
     *
     * @param openId
     * @return
     */
    @Override
    public WechatUserInfo getUserInfo(String openId) {

        String url = URL_GET_USER_INFO + openId;
        String response = getRestTemplate().getForObject(url, String.class);
        if (StringUtils.contains(response, "errcode")) {
            return null;
        }

        WechatUserInfo profile = null;
        try {
            profile = objectMapper.readValue(response, WechatUserInfo.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return profile;
    }
}
