package com.cheng.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.security.SocialUser;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author cheng
 *         2018/8/6 14:50
 */
@Component
@Transactional(rollbackFor = RuntimeException.class)
public class DemoUserDetailsServiceImpl implements UserDetailsService, SocialUserDetailsService {

    private Logger logger = LoggerFactory.getLogger(DemoUserDetailsServiceImpl.class);

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * TODO 根据 username 构建 UserDetail
     *
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        logger.info("表单登录用户名: " + username);
        return buildUser(username);
    }

    /**
     * TODO 根据 userId 构建 UserDetail
     *
     * @param userId
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public SocialUserDetails loadUserByUserId(String userId) throws UsernameNotFoundException {

        logger.info("社交登录用户id: " + userId);
        return buildUser(userId);
    }

    private SocialUserDetails buildUser(String userId) {

        // 根据用户名查找用户信息
        // 根据查找到的用户信息判断用户是否被冻结
        String password = passwordEncoder.encode("123");
        logger.info("数据库密码是: " + password);

        return new SocialUser(userId, password,
                true, true, true, true,
                // 添加 角色信息，用逗号分隔，配置用户授权
                AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_ADMIN"));
    }
}
