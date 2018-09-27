package com.cheng.security.rbac.domian;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

/**
 * 管理员（用户）
 *
 * @author cheng
 *         2018/9/27 12:10
 */
@Entity
public class Admin implements UserDetails {

    private static final long serialVersionUID = -3395390199084795271L;

    /**
     * 数据库主键
     */
    @Id
    @GeneratedValue
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 用户的所有角色
     */
    @OneToMany(mappedBy = "admin", cascade = CascadeType.REMOVE)
    private Set<RoleAdmin> roles = new HashSet<>();

    /**
     * 用户有权访问的所有 url，不持久化到数据库
     */
    @Transient
    private Set<String> urls = new HashSet<>();

    /**
     * 用户有权的所有资源 id，不持久化到数据库
     */
    @Transient
    private Set<Long> resourceIds = new HashSet<>();

    /**
     * 审计日志，记录条目创建时间，自动赋值，不需要程序员手工赋值
     */
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdTime;


    private void init(Set<?> data) {
        if (CollectionUtils.isEmpty(data)) {
            if (data == null) {
                data = new HashSet<>();
            }
        }
    }

    private void forEachResource(Consumer<Resource> consumer) {
        for (RoleAdmin role : roles) {
            for (RoleResource resource : role.getRole().getResources()) {
                consumer.accept(resource.getResource());
            }
        }
    }

    public Set<Long> getAllResourceIds() {
        init(resourceIds);
        forEachResource(resource -> resourceIds.add(resource.getId()));
        return resourceIds;
    }

    public Set<String> getUrls() {
        init(urls);
        forEachResource(resource -> urls.addAll(resource.getUrls()));
        return urls;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<RoleAdmin> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleAdmin> roles) {
        this.roles = roles;
    }

    public void setUrls(Set<String> urls) {
        this.urls = urls;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
