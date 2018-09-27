package com.cheng.security.rbac.service;

import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

/**
 * rbac 权限管理服务
 *
 * @author cheng
 *         2018/9/27 16:08
 */
public interface RbacService {

    /**
     * 判断是否拥有权限
     *
     * @param request
     * @return
     */
    boolean hasPermission(HttpServletRequest request, Authentication authentication);
}
