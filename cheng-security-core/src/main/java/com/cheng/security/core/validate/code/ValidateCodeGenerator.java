package com.cheng.security.core.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * 校验码生成器
 *
 * @author cheng
 *         2018/8/6 23:00
 */
public interface ValidateCodeGenerator {

    /**
     * 生成验证码
     *
     * @param request
     * @return
     */
    ImageCode generate(ServletWebRequest request);
}
