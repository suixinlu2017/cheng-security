package com.cheng.security.rbac.repository;

import com.cheng.security.rbac.domian.Admin;
import org.springframework.stereotype.Repository;

/**
 * @author cheng
 *         2018/9/27 15:23
 */
@Repository
public interface AdminRepository extends ChengRepository<Admin> {

    /**
     * 根据用户名查询用户信息
     *
     * @param username
     * @return
     */
    Admin findByUsername(String username);
}
