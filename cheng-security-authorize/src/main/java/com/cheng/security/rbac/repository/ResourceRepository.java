package com.cheng.security.rbac.repository;

import com.cheng.security.rbac.domian.Resource;
import org.springframework.stereotype.Repository;

/**
 * @author cheng
 *         2018/9/27 15:26
 */
@Repository
public interface ResourceRepository extends ChengRepository<Resource> {

    /**
     * 根据资源名查询资源信息
     *
     * @param username
     * @return
     */
    Resource findByName(String username);
}
