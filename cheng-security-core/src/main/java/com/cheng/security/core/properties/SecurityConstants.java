package com.cheng.security.core.properties;

/**
 * 安全验证相关静态常量
 *
 * @author cheng
 *         2018/8/7 19:27
 */
public interface SecurityConstants {

    /**
     * 默认的处理验证码的 url 前缀
     */
    String DEFAULT_VALIDATE_CODE_URL_PREFIX = "/code";

    /**
     * 当请求需要身份认证时，默认跳转的 url
     */
    String DEFAULT_AUTHENTICATION_URL = "/authentication/require";

    /**
     * 默认 用户名密码登录 请求处理 url
     */
    String DEFAULT_LOGIN_PROCESSING_URL_FORM = "/authentication/form";

    /**
     * 默认 手机验证码登录 请求处理 rul
     */
    String DEFAULT_LOGIN_PROCESSING_URL_MOBILE = "/authentication/mobile";

    /**
     * 默认 OPENID登录 请求处理 url
     */
    String DEFAULT_LOGIN_PROCESSING_URL_OPENID = "/authentication/openid";

    /**
     * 默认登录页面
     */
    String DEFAULT_LOGIN_PAGE_URL = "/cheng-signIn.html";

    /**
     * 验证图片验证码时，http请求中默认携带的图片验证码信息的参数的名称
     */
    String DEFAULT_PARAMETER_NAME_CODE_IMAGE = "imageCode";

    /**
     * 验证短信验证码时，http请求中默认携带的短信验证码信息的参数的名称
     */
    String DEFAULT_PARAMETER_NAME_CODE_SMS = "smsCode";

    /**
     * 发送短信验证码 或 验证短信验证码时，传递手机号参数的名称
     */
    String DEFAULT_PARAMETER_NAME_MOBILE = "mobile";

    /**
     * openid 参数名
     */
    String DEFAULT_PARAMETER_NAME_OPENID = "openId";

    /**
     * providerId 参数名
     */
    String DEFAULT_PARAMETER_NAME_PROVIDERID = "providerId";

    /**
     * session 失效默认跳转地址
     */
    String DEFAULT_SESSION_INVALID_URL = "/session/invalid";
}
