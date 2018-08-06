package com.cheng.security.core.properties;

/**
 * 认证成功后的响应方式
 *
 * @author cheng
 *         2018/8/6 21:28
 */
public enum LoginResponseType {

    /**
     * 跳转
     */
    REDIRECT,
    /**
     * 返回 json
     */
    JSON
}
