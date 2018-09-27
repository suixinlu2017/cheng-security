package com.cheng.security.rbac.service;

import com.cheng.security.rbac.dto.AdminCondition;
import com.cheng.security.rbac.dto.AdminInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * 管理员服务
 *
 * @author cheng
 *         2018/9/27 16:06
 */
public interface AdminService {

    /**
     * 创建管理员
     *
     * @param adminInfo
     * @return
     */
    AdminInfo create(AdminInfo adminInfo);

    /**
     * 修改管理员
     *
     * @param adminInfo
     * @return
     */
    AdminInfo update(AdminInfo adminInfo);

    /**
     * 删除管理员
     *
     * @param id
     * @return
     */
    void delete(Long id);

    /**
     * 获取管理员详细信息
     *
     * @param id
     * @return
     */
    AdminInfo getInfo(Long id);

    /**
     * 分页查询管理员
     *
     * @param condition
     * @param pageable
     * @return
     */
    Page<AdminInfo> query(AdminCondition condition, Pageable pageable);
}