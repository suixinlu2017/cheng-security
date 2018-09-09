package com.cheng.security.core.authentication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;

/**
 * 默认的 SocialUserDetailsService 实现
 * <p>
 * 不做任何处理，只在控制台打印一句日志，然后抛出异常，提醒业务系统自己配置 SocialUserDetailsService
 *
 * @author cheng
 *         2018/9/9 16:28
 */
public class DefaultSocialUserDetailsServiceImpl implements SocialUserDetailsService {

    private Logger logger = LoggerFactory.getLogger(DefaultUserDetailsServiceImpl.class);

    @Override
    public SocialUserDetails loadUserByUserId(String userId) throws UsernameNotFoundException {
        logger.warn("请配置 SocialUserDetailsService 接口的实现");
        throw new UsernameNotFoundException(userId);
    }
}
