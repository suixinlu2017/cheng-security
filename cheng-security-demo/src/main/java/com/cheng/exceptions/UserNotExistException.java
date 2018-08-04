package com.cheng.exceptions;

/**
 * 自定义异常
 *
 * @author cheng
 *         2018/8/4 20:19
 */
public class UserNotExistException extends RuntimeException {

    private String id;

    public UserNotExistException(String id) {
        super("user not exist");
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
