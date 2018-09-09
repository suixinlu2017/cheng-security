package com.cheng.security;

import com.cheng.security.core.authorize.AuthorizeConfigProvider;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

/**
 * @author cheng
 *         2018/9/8 14:40
 */
@Component
public class DemoAuthorizeConfigProvider implements AuthorizeConfigProvider {

    /**
     * demo 项目授权配置
     *
     * @param config
     * @return
     */
    @Override
    public boolean config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {

        // demo 项目用户注册功能权限配置
        config.antMatchers(HttpMethod.POST, "/user/register").permitAll();

        /*config
                .antMatchers("/demo.html")
                .hasRole("ADMIN");*/

        /*config.
                anyRequest().access("@rbacService.hasPermission(request,authentication)");*/


        return false;
    }
}
