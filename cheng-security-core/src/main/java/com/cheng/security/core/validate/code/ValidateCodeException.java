package com.cheng.security.core.validate.code;


import org.springframework.security.core.AuthenticationException;

/**
 * @author cheng
 *         2018/8/6 20:08
 */
public class ValidateCodeException extends AuthenticationException {

    private static final long serialVersionUID = -4656527698605469992L;

    public ValidateCodeException(String message) {
        super(message);
    }
}
