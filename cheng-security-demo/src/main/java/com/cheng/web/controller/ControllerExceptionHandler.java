package com.cheng.web.controller;

import com.cheng.exceptions.UserNotExistException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * 自定义异常处理
 *
 * @author cheng
 *         2018/8/4 20:22
 */
@ControllerAdvice
public class ControllerExceptionHandler {

    /**
     * 拦截抛出 UserNotExistException.class 异常的所有方法
     *
     * @return
     */
    @ExceptionHandler(UserNotExistException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, Object> handlerUserNotExistException(UserNotExistException exception) {

        Map<String, Object> result = new HashMap<>();
        result.put("id", exception.getId());
        result.put("message", exception.getMessage());

        return result;
    }
}
