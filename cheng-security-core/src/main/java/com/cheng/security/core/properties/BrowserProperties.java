package com.cheng.security.core.properties;


/**
 * 浏览器环境配置项
 *
 * @author cheng
 *         2018/8/6 16:01
 */

public class BrowserProperties {

    /**
     * session 管理配置项
     */
    private SessionProperties session = new SessionProperties();

    /**
     * 社交登录，如果需要用户注册，跳转的页面
     */
    private String signUpUrl = "/cheng-signUp.html";

    /**
     * 退出成功时跳转的 url，如果配置了，则跳到指定的 url，如果没配置，则返回 json 数据
     */
    private String signOutUrl;

    private String loginPage = SecurityConstants.DEFAULT_LOGIN_PAGE_URL;

    /**
     * 登录响应的方式，默认是 json
     */
    private LoginResponseType loginResponseType = LoginResponseType.JSON;

    /**
     * '记住我' 功能的有效时间，默认为1小时
     */
    private int rememberMeSeconds = 3600;

    public SessionProperties getSession() {
        return session;
    }

    public void setSession(SessionProperties session) {
        this.session = session;
    }

    public String getSignUpUrl() {
        return signUpUrl;
    }

    public void setSignUpUrl(String signUpUrl) {
        this.signUpUrl = signUpUrl;
    }

    public String getSignOutUrl() {
        return signOutUrl;
    }

    public void setSignOutUrl(String signOutUrl) {
        this.signOutUrl = signOutUrl;
    }

    public String getLoginPage() {
        return loginPage;
    }

    public void setLoginPage(String loginPage) {
        this.loginPage = loginPage;
    }

    public LoginResponseType getLoginResponseType() {
        return loginResponseType;
    }

    public void setLoginResponseType(LoginResponseType loginResponseType) {
        this.loginResponseType = loginResponseType;
    }

    public int getRememberMeSeconds() {
        return rememberMeSeconds;
    }

    public void setRememberMeSeconds(int rememberMeSeconds) {
        this.rememberMeSeconds = rememberMeSeconds;
    }
}
