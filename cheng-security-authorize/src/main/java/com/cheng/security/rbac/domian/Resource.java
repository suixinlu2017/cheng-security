package com.cheng.security.rbac.domian;

import com.cheng.security.rbac.dto.ResourceInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * 需要控制权限的资源，以业务人员能看懂的 name 呈现，实际关联到一个或多个 url 上
 * <p>
 * 树形结构
 *
 * @author cheng
 *         2018/9/27 12:33
 */
@Entity
public class Resource {

    /**
     * 数据库表主键
     */
    @Id
    @GeneratedValue
    private Long id;

    /**
     * 资源名称，如：xx菜单，xx按钮
     */
    private String name;

    /**
     * 资源链接
     */
    private String link;

    /**
     * 图标
     */
    private String icon;

    /**
     * 资源类型
     */
    @Enumerated(EnumType.STRING)
    private ResourceType type;

    /**
     * 实际需要控制权限的 url
     */
    @ElementCollection
    private Set<String> urls;

    /**
     * 父资源
     */
    @ManyToOne
    private Resource parent;

    /**
     * 子资源
     */
    @OneToMany(mappedBy = "parent")
    @OrderBy("sort ASC")
    private List<Resource> children = new ArrayList<>();

    /**
     * 序号
     */
    private int sort;

    /**
     * 审计日志，记录条目创建时间，自动赋值，不需要程序员手工赋值
     */
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdTime;

    /**
     * 返回当前登录用户的可访问资源
     *
     * @param admin
     * @return
     */
    public ResourceInfo toTree(Admin admin) {

        ResourceInfo result = new ResourceInfo();
        BeanUtils.copyProperties(this, result);

        Set<Long> resourceIds = admin.getAllResourceIds();
        List<ResourceInfo> children = new ArrayList<>();
        for (Resource child : getChildren()) {
            if (StringUtils.equals(admin.getUsername(), "admin") || resourceIds.contains(child.getId())) {
                children.add(child.toTree(admin));
            }
        }
        result.setChildren(children);

        return result;
    }

    /**
     * 为当前资源添加子资源
     *
     * @param child
     */
    public void addChild(Resource child) {
        children.add(child);
        child.setParent(this);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public ResourceType getType() {
        return type;
    }

    public void setType(ResourceType type) {
        this.type = type;
    }

    public Set<String> getUrls() {
        return urls;
    }

    public void setUrls(Set<String> urls) {
        this.urls = urls;
    }

    public Resource getParent() {
        return parent;
    }

    public void setParent(Resource parent) {
        this.parent = parent;
    }

    public List<Resource> getChildren() {
        return children;
    }

    public void setChildren(List<Resource> children) {
        this.children = children;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }
}
