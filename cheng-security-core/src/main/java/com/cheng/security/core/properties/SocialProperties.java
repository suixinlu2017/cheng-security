package com.cheng.security.core.properties;

/**
 * 社交登录配置项
 *
 * @author cheng
 *         2018/8/8 20:35
 */
public class SocialProperties {

    private QQProperties qq = new QQProperties();

    public QQProperties getQq() {
        return qq;
    }

    public void setQq(QQProperties qq) {
        this.qq = qq;
    }
}
