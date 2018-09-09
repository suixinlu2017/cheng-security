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
     * 登录页面，当引发登录行为的 url 以 html 结尾时，会跳到这里配置的 url 上
     */
    private String signInPage = SecurityConstants.DEFAULT_SIGN_IN_PAGE_URL;

    /**
     * 登录成功后跳转的地址，如果设置了此属性，则登录成功后总是会跳到这个地址上
     * 只在 signInSuccessUrl 为 REDIRECT 时生效
     */
    private String signInSuccessUrl;

    /**
     * 登录响应的方式，默认是 json
     */
    private LoginResponseType signInResponseType = LoginResponseType.JSON;

    /**
     * 社交登录，如果需要用户注册，跳转的页面
     */
    private String signUpUrl = SecurityConstants.DEFAULT_SIGN_UP_URL;

    /**
     * 退出成功时跳转的 url，如果配置了，则跳到指定的 url，如果没配置，则返回 json 数据
     */
    private String signOutUrl;

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

    public String getSignInPage() {
        return signInPage;
    }

    public void setSignInPage(String signInPage) {
        this.signInPage = signInPage;
    }

    public String getSignInSuccessUrl() {
        return signInSuccessUrl;
    }

    public void setSignInSuccessUrl(String signInSuccessUrl) {
        this.signInSuccessUrl = signInSuccessUrl;
    }

    public LoginResponseType getSignInResponseType() {
        return signInResponseType;
    }

    public void setSignInResponseType(LoginResponseType signInResponseType) {
        this.signInResponseType = signInResponseType;
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

    public int getRememberMeSeconds() {
        return rememberMeSeconds;
    }

    public void setRememberMeSeconds(int rememberMeSeconds) {
        this.rememberMeSeconds = rememberMeSeconds;
    }
}
