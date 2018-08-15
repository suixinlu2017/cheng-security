package com.cheng.security.core.properties;

/**
 * session 管理相关配置项
 *
 * @author cheng
 *         2018/8/15 22:11
 */
public class SessionProperties {

    /**
     * 同一个用户在系统中的最大 session 数，默认 1
     */
    private int maximumSession = 1;

    /**
     * 达到最大 session 时是否阻止新的登录请求，默认为 false，不阻止，新的登录会使得老的登录失效掉
     */
    private boolean maxSessionPreventsLogin;

    /**
     * session 失效跳转地址
     */
    private String sessionInvalidUrl = SecurityConstants.DEFAULT_SESSION_INVALID_URL;

    public int getMaximumSession() {
        return maximumSession;
    }

    public void setMaximumSession(int maximumSession) {
        this.maximumSession = maximumSession;
    }

    public boolean isMaxSessionPreventsLogin() {
        return maxSessionPreventsLogin;
    }

    public void setMaxSessionPreventsLogin(boolean maxSessionPreventsLogin) {
        this.maxSessionPreventsLogin = maxSessionPreventsLogin;
    }

    public String getSessionInvalidUrl() {
        return sessionInvalidUrl;
    }

    public void setSessionInvalidUrl(String sessionInvalidUrl) {
        this.sessionInvalidUrl = sessionInvalidUrl;
    }
}
