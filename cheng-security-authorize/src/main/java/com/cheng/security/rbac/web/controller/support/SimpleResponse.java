package com.cheng.security.rbac.web.controller.support;

/**
 * @author cheng
 *         2018/9/27 13:39
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
