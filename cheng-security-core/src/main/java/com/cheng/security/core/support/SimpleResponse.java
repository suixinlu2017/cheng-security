package com.cheng.security.core.support;

/**
 * 简单响应的封装类
 *
 * @author cheng
 *         2018/8/6 15:53
 */
public class SimpleResponse {

    private Object content;

    public SimpleResponse(Object content) {
        this.content = content;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }
}
