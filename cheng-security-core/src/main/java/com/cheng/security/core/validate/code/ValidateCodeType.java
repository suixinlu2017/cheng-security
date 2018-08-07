package com.cheng.security.core.validate.code;

import com.cheng.security.core.properties.SecurityConstants;

/**
 * 校验码类型
 *
 * @author cheng
 *         2018/8/7 19:39
 */
public enum ValidateCodeType {
    /**
     * 短信验证码
     */
    SMS {
        @Override
        public String getParamNameOnValidate() {
            return SecurityConstants.DEFAULT_PARAMETER_NAME_CODE_SMS;
        }
    },
    /**
     * 图片验证码
     */
    IMAGE {
        @Override
        public String getParamNameOnValidate() {
            return SecurityConstants.DEFAULT_PARAMETER_NAME_CODE_IMAGE;
        }
    };

    /**
     * 校验时从请求总获取的参数的名字
     *
     * @return
     */
    public abstract String getParamNameOnValidate();
}
