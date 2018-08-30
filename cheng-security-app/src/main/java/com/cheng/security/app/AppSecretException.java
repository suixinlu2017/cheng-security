package com.cheng.security.app;

/**
 * @author cheng
 *         2018/08/30 15:11
 */
public class AppSecretException extends RuntimeException {

    private static final long serialVersionUID = 3268932345331125128L;

    public AppSecretException(String msg) {
        super(msg);
    }
}
