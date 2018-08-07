package com.cheng.security.core.validate.code.sms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 默认的短信验证码发送器
 *
 * @author cheng
 *         2018/8/7 14:08
 */
public class DefaultSmsCodeSender implements SmsCodeSender {

    private Logger logger = LoggerFactory.getLogger(DefaultSmsCodeSender.class);

    @Override
    public void send(String mobile, String code) {
        // TODO 短信验证码发送器
        logger.warn("请配置真实的短信验证码发送器(SmsCodeSender)");
        logger.info("向手机: " + mobile + ",发送短信验证码: " + code);
    }
}
