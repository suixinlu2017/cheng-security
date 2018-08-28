package com.cheng.security.core.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * 验证码存取器
 *
 * @author cheng
 *         2018/08/28 18:02
 */
public interface ValidateCodeRepository {

    /**
     * 保存验证码
     *
     * @param request
     * @param code
     * @param validateCodeType
     */
    void save(ServletWebRequest request, ValidateCode code, ValidateCodeType validateCodeType);

    /**
     * 获取验证码
     *
     * @param request
     * @param codeType
     * @return
     */
    ValidateCode get(ServletWebRequest request, ValidateCodeType codeType);

    /**
     * 移除验证码
     *
     * @param request
     * @param codeType
     */
    void remove(ServletWebRequest request, ValidateCodeType codeType);
}
