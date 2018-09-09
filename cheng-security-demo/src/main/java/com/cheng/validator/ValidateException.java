package com.cheng.validator;

import org.springframework.validation.ObjectError;

import java.util.List;

/**
 * @author cheng
 *         2018/9/9 16:10
 */
public class ValidateException extends RuntimeException {

    private static final long serialVersionUID = 8055208504463675790L;

    private List<ObjectError> errors;

    public ValidateException(List<ObjectError> errors) {
        this.errors = errors;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public List<ObjectError> getErrors() {
        return errors;
    }

    public void setErrors(List<ObjectError> errors) {
        this.errors = errors;
    }
}
