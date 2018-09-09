package com.cheng.security.core.social.support;

import org.springframework.social.security.SocialAuthenticationFilter;

/**
 * SocialAuthenticationFilter 后处理器，用于在不同的环境下个性化社交登录配置
 *
 * @author cheng
 *         2018/08/29 11:11
 */
public interface SocialAuthenticationFilterPostProcessor {

    /**
     * 处理方法
     *
     * @param socialAuthenticationFilter
     */
    void process(SocialAuthenticationFilter socialAuthenticationFilter);
}
