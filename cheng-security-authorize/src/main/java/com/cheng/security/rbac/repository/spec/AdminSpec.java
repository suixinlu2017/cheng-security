package com.cheng.security.rbac.repository.spec;

import com.cheng.security.rbac.domian.Admin;
import com.cheng.security.rbac.dto.AdminCondition;
import com.cheng.security.rbac.repository.support.AbstractChengSpecification;
import com.cheng.security.rbac.repository.support.QueryWrapper;

/**
 * @author cheng
 *         2018/9/27 13:43
 */
public class AdminSpec extends AbstractChengSpecification<Admin, AdminCondition> {


    public AdminSpec(AdminCondition condition) {
        super(condition);
    }

    @Override
    protected void addCondition(QueryWrapper<Admin> queryWrapper) {
        addLikeCondition(queryWrapper,"username");
    }
}
