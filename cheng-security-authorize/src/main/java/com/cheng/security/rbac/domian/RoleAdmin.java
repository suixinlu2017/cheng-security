package com.cheng.security.rbac.domian;

import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

/**
 * 角色用户关系表
 *
 * @author cheng
 *         2018/9/27 12:29
 */
@Entity
public class RoleAdmin {

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
     * 管理员
     */
    @ManyToOne
    private Admin admin;

    /**
     * 审计日志，记录条目创建时间
     * 自动赋值，不需要程序员手工赋值
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

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RoleAdmin roleAdmin = (RoleAdmin) o;
        return Objects.equals(id, roleAdmin.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
