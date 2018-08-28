package com.cheng.security.app.validate.code.impl;

import com.cheng.security.core.validate.code.ValidateCode;
import com.cheng.security.core.validate.code.ValidateCodeException;
import com.cheng.security.core.validate.code.ValidateCodeRepository;
import com.cheng.security.core.validate.code.ValidateCodeType;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import java.util.concurrent.TimeUnit;

/**
 * 基于 redis 的验证码存取器，避免由于没有 session 导致无法存取验证码的问题
 *
 * @author cheng
 *         2018/8/28 21:13
 */
@Component
public class RedisValidateCodeRepository implements ValidateCodeRepository {

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    @Override
    public void save(ServletWebRequest request, ValidateCode code, ValidateCodeType validateCodeType) {
        redisTemplate.opsForValue().set(buildKey(request, validateCodeType), code, 30, TimeUnit.MINUTES);
    }

    @Override
    public ValidateCode get(ServletWebRequest request, ValidateCodeType codeType) {

        Object value = redisTemplate.opsForValue().get(buildKey(request, codeType));
        if (value == null) {
            return null;
        }

        return (ValidateCode) value;
    }

    @Override
    public void remove(ServletWebRequest request, ValidateCodeType codeType) {
        redisTemplate.delete(buildKey(request, codeType));
    }

    private String buildKey(ServletWebRequest request, ValidateCodeType type) {

        String deviceId = request.getHeader("deviceId");
        if (StringUtils.isBlank(deviceId)) {
            throw new ValidateCodeException("请在请求头中携带 deviceId 参数");
        }

        return "code: " + type.toString().toUpperCase() + ": " + deviceId;
    }
}