package com.cheng.security.core.social;

import com.cheng.security.core.social.support.SocialUserInfo;
import org.springframework.social.connect.Connection;


/**
 * @author cheng
 *         2018/9/9 12:29
 */
public abstract class AbstractSocialController {

    /**
     * 根据 connection 信息构建 SocialUserINfo
     *
     * @param connection
     * @return
     */
    protected SocialUserInfo buildSocialUserInfo(Connection<?> connection) {

        SocialUserInfo userInfo = new SocialUserInfo();
        userInfo.setProviderId(connection.getKey().getProviderId());
        userInfo.setProviderUserId(connection.getKey().getProviderUserId());
        userInfo.setNickname(connection.getDisplayName());
        userInfo.setHeadPortrait(connection.getImageUrl());
        return userInfo;
    }
}
