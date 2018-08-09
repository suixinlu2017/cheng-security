package com.cheng.security.core.properties;


/**
 * 浏览器环境配置项
 *
 * @author cheng
 *         2018/8/6 16:01
 */

public class BrowserProperties {

    private String singUpUrl = "/cheng-signUp.html";

    private String loginPage = SecurityConstants.DEFAULT_LOGIN_PAGE_URL;

    private LoginResponseType loginResponseType = LoginResponseType.JSON;

    private int rememberMeSeconds = 3600;

    public String getSingUpUrl() {
        return singUpUrl;
    }

    public void setSingUpUrl(String singUpUrl) {
        this.singUpUrl = singUpUrl;
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
