package com.cheng.security.core.validate.code;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 验证码信息封装类
 *
 * @author cheng
 *         2018/8/7 14:00
 */
public class ValidateCode implements Serializable {

    private static final long serialVersionUID = 7592751197469228875L;

    private String code;

    private LocalDateTime expireTime;

    public ValidateCode(String code, int expireIn) {
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
    }

    public ValidateCode(String code, LocalDateTime expireTime) {
        this.code = code;
        this.expireTime = expireTime;
    }

    public boolean isExpired() {
        return LocalDateTime.now().isAfter(expireTime);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDateTime getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(LocalDateTime expireTime) {
        this.expireTime = expireTime;
    }
}
