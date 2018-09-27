package com.cheng.security.rbac.service;

import com.cheng.security.rbac.dto.RoleInfo;

import java.util.List;

/**
 * 角色服务
 *
 * @author cheng
 *         2018/9/27 16:12
 */
public interface RoleService {

    /**
     * 创建角色
     *
     * @param info
     * @return
     */
    RoleInfo create(RoleInfo info);

    /**
     * 修改角色
     *
     * @param info
     * @return
     */
    RoleInfo update(RoleInfo info);

    /**
     * 删除角色
     *
     * @param id
     * @return
     */
    void delete(Long id);

    /**
     * 获取角色详细信息
     *
     * @param id
     * @return
     */
    RoleInfo getInfo(Long id);

    /**
     * 查询所有角色
     *
     * @return
     */
    List<RoleInfo> findAll();

    /**
     * 根据角色ID获取所有资源
     *
     * @param id
     * @return
     */
    String[] getRoleResources(Long id);

    /**
     * 给角色添加资源ID
     *
     * @param id
     * @param resourceIds
     */
    void setRoleResources(Long id, String resourceIds);
}
