package com.cheng.security.rbac.service.impl;

import com.cheng.security.rbac.domian.Admin;
import com.cheng.security.rbac.service.RbacService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;

/**
 * @author cheng
 *         2018/9/27 16:23
 */
@Component("rbacService")
public class RbacServiceImpl implements RbacService {

    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {

        Object principal = authentication.getPrincipal();

        boolean hasPermission = false;

        if (principal instanceof Admin) {
            // 如果用户名是 admin，就永远返回 true
            if (StringUtils.pathEquals(((Admin) principal).getUsername(), "admin")) {
                hasPermission = true;
            } else {
                // 读取用户所拥有权限的所有 url
                Set<String> urls = ((Admin) principal).getUrls();
                for (String url : urls) {
                    if (antPathMatcher.match(url, request.getRequestURI())) {
                        hasPermission = true;
                        break;
                    }
                }
            }
        }

        return hasPermission;
    }
}
