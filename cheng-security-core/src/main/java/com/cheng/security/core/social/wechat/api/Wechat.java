package com.cheng.security.core.social.wechat.api;

/**
 * 微信API调用接口
 *
 * @author cheng
 *         2018/8/13 20:04
 */
public interface Wechat {

    /**
     * 获取用户信息
     *
     * @param openId
     * @return
     */
    WechatUserInfo getUserInfo(String openId);
}
