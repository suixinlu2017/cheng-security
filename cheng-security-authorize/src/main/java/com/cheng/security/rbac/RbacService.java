package com.cheng.security.rbac;

import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

/**
 * @author cheng
 *         2018/9/9 13:08
 */
public interface RbacService {

    /**
     * 根据 rbac数据库 配置判断用户是否有权限
     *
     * @param request
     * @param authentication
     * @return
     */
    boolean hasPermission(HttpServletRequest request, Authentication authentication);
}
