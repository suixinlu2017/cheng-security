package com.cheng.security.rbac.service;

import com.cheng.security.rbac.dto.ResourceInfo;

/**
 * 资源服务
 *
 * @author cheng
 *         2018/9/27 16:10
 */
public interface ResourceService {

    /**
     * 获取资源树
     *
     * @param userId 用户ID
     * @return
     */
    ResourceInfo getTree(Long userId);

    /**
     * 根据资源ID获取资源信息
     *
     * @param id 资源ID
     * @return
     */
    ResourceInfo getInfo(Long id);

    /**
     * 新增资源
     *
     * @param info 页面传入的资源信息
     * @return
     */
    ResourceInfo create(ResourceInfo info);

    /**
     * 更新资源
     *
     * @param info 页面传入的资源信息
     * @return
     */
    ResourceInfo update(ResourceInfo info);

    /**
     * 根据指定ID删除资源信息
     *
     * @param id 资源ID
     * @return
     */
    void delete(Long id);

    /**
     * 上移/下移资源
     *
     * @param id
     * @param up
     * @return
     */
    Long move(Long id, boolean up);
}
