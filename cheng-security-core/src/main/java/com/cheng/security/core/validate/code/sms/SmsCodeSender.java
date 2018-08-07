package com.cheng.security.core.validate.code.sms;

/**
 * 短信验证码发送器接口
 *
 * @author cheng
 *         2018/8/7 14:07
 */
public interface SmsCodeSender {

    /**
     * 短信验证码发送
     *
     * @param mobile
     * @param code
     */
    void send(String mobile, String code);
}
