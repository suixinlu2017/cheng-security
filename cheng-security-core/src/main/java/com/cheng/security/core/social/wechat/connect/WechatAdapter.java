package com.cheng.security.core.social.wechat.connect;

import com.cheng.security.core.social.wechat.api.Wechat;
import com.cheng.security.core.social.wechat.api.WechatUserInfo;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;

/**
 * 微信API适配器，将微信API的数据模型转为 Spring social 的标准模型
 *
 * @author cheng
 *         2018/8/13 21:08
 */
public class WechatAdapter implements ApiAdapter<Wechat> {

    private String openId;

    public WechatAdapter() {
    }

    public WechatAdapter(String openId) {
        this.openId = openId;
    }

    @Override
    public boolean test(Wechat api) {
        return true;
    }

    @Override
    public void setConnectionValues(Wechat api, ConnectionValues values) {

        WechatUserInfo profile = api.getUserInfo(openId);
        values.setProviderUserId(profile.getOpenid());
        values.setDisplayName(profile.getNickname());
        values.setImageUrl(profile.getHeadimgurl());

    }

    @Override
    public UserProfile fetchUserProfile(Wechat api) {
        return null;
    }

    @Override
    public void updateStatus(Wechat api, String message) {
        // do nothing
    }
}
