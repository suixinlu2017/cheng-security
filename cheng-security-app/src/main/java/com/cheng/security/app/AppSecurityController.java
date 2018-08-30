package com.cheng.security.app;

import com.cheng.security.app.social.AppSignUpUtils;
import com.cheng.security.core.support.SocialUserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * @author cheng
 *         2018/08/30 15:39
 */
@RestController
public class AppSecurityController {

    @Autowired
    ProviderSignInUtils providerSignInUtils;

    @Autowired
    private AppSignUpUtils appSignUpUtils;


    /**
     * app注册跳转这里，返回401和用户信息给前端
     *
     * @param request
     * @return
     */
    @GetMapping("/social/singUp")
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public SocialUserInfo getSocialUserInfo(HttpServletRequest request) {

        SocialUserInfo userInfo = new SocialUserInfo();
        Connection<?> connection = providerSignInUtils.getConnectionFromSession(new ServletWebRequest(request));

        userInfo.setProviderId(connection.getKey().getProviderId());
        userInfo.setProviderUserId(connection.getKey().getProviderUserId());
        userInfo.setNickname(connection.getDisplayName());
        userInfo.setHeadPortrait(connection.getImageUrl());

        appSignUpUtils.saveConnectionData(new ServletWebRequest(request), connection.createData());

        return userInfo;
    }
}
