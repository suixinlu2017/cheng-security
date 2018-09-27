package com.cheng.security.rbac.service.impl;

import com.cheng.security.rbac.domian.Role;
import com.cheng.security.rbac.domian.RoleResource;
import com.cheng.security.rbac.dto.RoleInfo;
import com.cheng.security.rbac.repository.ResourceRepository;
import com.cheng.security.rbac.repository.RoleRepository;
import com.cheng.security.rbac.repository.RoleResourceRepository;
import com.cheng.security.rbac.repository.support.QueryResultConverter;
import com.cheng.security.rbac.service.RoleService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author cheng
 *         2018/9/27 16:34
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ResourceRepository resourceRepository;

    @Autowired
    private RoleResourceRepository roleResourceRepository;

    @Override
    public RoleInfo create(RoleInfo info) {

        Role role = new Role();
        BeanUtils.copyProperties(info, role);
        info.setId(roleRepository.save(role).getId());
        return info;
    }

    @Override
    public RoleInfo update(RoleInfo info) {

        Role role = roleRepository.findOne(info.getId());
        BeanUtils.copyProperties(info, role);
        return info;
    }

    @Override
    public void delete(Long id) {
        Role role = roleRepository.findOne(id);
        if (CollectionUtils.isNotEmpty(role.getAdmins())) {
            throw new RuntimeException("不能删除有下挂用户的角色");
        }

        roleRepository.delete(id);
    }

    @Override
    public RoleInfo getInfo(Long id) {

        Role role = roleRepository.findOne(id);
        RoleInfo info = new RoleInfo();
        BeanUtils.copyProperties(role, info);
        return info;
    }

    @Override
    public List<RoleInfo> findAll() {
        return QueryResultConverter.convert(roleRepository.findAll(), RoleInfo.class);
    }

    @Override
    public String[] getRoleResources(Long id) {

        Role role = roleRepository.findOne(id);
        Set<String> resourceIds = new HashSet<>();
        for (RoleResource resource : role.getResources()) {
            resourceIds.add(resource.getResource().getId().toString());
        }

        return resourceIds.toArray(new String[0]);
    }

    @Override
    public void setRoleResources(Long roleId, String resourceIds) {

        resourceIds = StringUtils.removeEnd(resourceIds, ",");
        Role role = roleRepository.findOne(roleId);
        roleResourceRepository.delete(role.getResources());

        String[] resourceIdArray = StringUtils.splitByWholeSeparatorPreserveAllTokens(resourceIds, ",");
        for (String resourceId : resourceIdArray) {
            RoleResource roleResource = new RoleResource();
            roleResource.setRole(role);
            roleResource.setResource(resourceRepository.getOne(new Long(resourceId)));
            roleResourceRepository.save(roleResource);
        }
    }
}
