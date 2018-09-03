package com.cheng.security.browser.validate.code.impl;

import com.cheng.security.core.validate.code.ValidateCode;
import com.cheng.security.core.validate.code.ValidateCodeRepository;
import com.cheng.security.core.validate.code.ValidateCodeType;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * 基于 session 的验证码存取器
 *
 * @author cheng
 *         2018/8/28 21:03
 */
@Component
public class SessionValidateCodeRepository implements ValidateCodeRepository {

    /**
     * 验证码放入 session 时的前缀
     */
    private static final String SESSION_KEY_PREFIX = "SESSION_KEY_FOR_CODE_";

    /**
     * 操作 session 的工具类
     */
    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    @Override
    public void save(ServletWebRequest request, ValidateCode code, ValidateCodeType validateCodeType) {
        sessionStrategy.setAttribute(request, getSessionKey(request, validateCodeType), code);
    }

    @Override
    public ValidateCode get(ServletWebRequest request, ValidateCodeType validateCodeType) {
        return (ValidateCode) sessionStrategy.getAttribute(request, getSessionKey(request, validateCodeType));
    }

    @Override
    public void remove(ServletWebRequest request, ValidateCodeType codeType) {
        sessionStrategy.removeAttribute(request, getSessionKey(request, codeType));
    }

    /**
     * 构建验证码放入 session 时的key
     *
     * @param request
     * @param codeType
     * @return
     */
    private String getSessionKey(ServletWebRequest request, ValidateCodeType codeType) {
        return SESSION_KEY_PREFIX + codeType.toString().toUpperCase();
    }
}
