package com.cheng.security.rbac.service.impl;

import com.cheng.security.rbac.domian.Admin;
import com.cheng.security.rbac.domian.Resource;
import com.cheng.security.rbac.dto.ResourceInfo;
import com.cheng.security.rbac.repository.AdminRepository;
import com.cheng.security.rbac.repository.ResourceRepository;
import com.cheng.security.rbac.service.ResourceService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author cheng
 *         2018/9/27 16:27
 */
@Service
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private ResourceRepository resourceRepository;

    @Autowired
    private AdminRepository adminRepository;


    @Override
    public ResourceInfo getTree(Long userId) {
        Admin admin = adminRepository.findOne(userId);
        return resourceRepository.findByName("根节点").toTree(admin);
    }

    @Override
    public ResourceInfo getInfo(Long id) {
        Resource resource = resourceRepository.findOne(id);
        ResourceInfo resourceInfo = new ResourceInfo();
        BeanUtils.copyProperties(resource, resourceInfo);
        return resourceInfo;
    }

    @Override
    public ResourceInfo create(ResourceInfo info) {

        Resource parent = resourceRepository.findOne(info.getParentId());
        if (parent == null) {
            parent = resourceRepository.findByName("根节点");
        }

        Resource resource = new Resource();
        BeanUtils.copyProperties(info, resource);
        parent.addChild(resource);
        info.setId(resourceRepository.save(resource).getId());

        return info;
    }

    @Override
    public ResourceInfo update(ResourceInfo info) {

        Resource resource = resourceRepository.findOne(info.getId());
        BeanUtils.copyProperties(info,resource);
        return info;
    }

    @Override
    public void delete(Long id) {
        resourceRepository.delete(id);
    }

    @Override
    public Long move(Long id, boolean up) {

        Resource resource = resourceRepository.findOne(id);
        int index = resource.getSort();
        List<Resource> children = resource.getParent().getChildren();
        for (int i = 0; i < children.size(); i++) {
            Resource current = children.get(i);
            if (current.getId().equals(id)) {
                if (up) {
                    if (i != 0) {
                        Resource pre = children.get(i - 1);
                        resource.setSort(pre.getSort());
                        pre.setSort(index);
                        resourceRepository.save(pre);
                    }
                } else {
                    if (i != children.size() - 1) {
                        Resource next = children.get(i+1);
                        resource.setSort(next.getSort());
                        next.setSort(index);
                        resourceRepository.save(next);
                    }
                }
            }
        }

        resourceRepository.save(resource);
        return resource.getParent().getId();
    }
}
