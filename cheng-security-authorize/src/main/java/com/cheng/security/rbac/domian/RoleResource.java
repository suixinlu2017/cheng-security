package com.cheng.security.rbac.domian;

import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

/**
 * 角色资源关系表
 *
 * @author cheng
 *         2018/9/27 12:32
 */
@Entity
public class RoleResource {

    /**
     * 数据库表主键
     */
    @Id
    @GeneratedValue
    private Long id;

    /**
     * 角色
     */
    @ManyToOne
    private Role role;

    /**
     * 资源
     */
    @ManyToOne
    private Resource resource;

    /**
     * 审计日志，记录条目创建时间，自动赋值，不需要程序员手工赋值
     */
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RoleResource that = (RoleResource) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
