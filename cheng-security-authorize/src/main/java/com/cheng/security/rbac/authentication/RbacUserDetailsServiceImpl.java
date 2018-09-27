package com.cheng.security.rbac.authentication;

import com.cheng.security.rbac.domian.Admin;
import com.cheng.security.rbac.repository.AdminRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author cheng
 *         2018/9/27 15:31
 */
@Component
@Transactional(rollbackFor = Exception.class)
public class RbacUserDetailsServiceImpl implements UserDetailsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RbacUserDetailsServiceImpl.class);

    @Autowired
    private AdminRepository adminRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        LOGGER.info("表单登录用户名: " + username);
        Admin admin = adminRepository.findByUsername(username);
        admin.getUrls();

        return admin;
    }
}
