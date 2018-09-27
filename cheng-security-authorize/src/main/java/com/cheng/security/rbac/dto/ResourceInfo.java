package com.cheng.security.rbac.dto;

import com.cheng.security.rbac.domian.ResourceType;

import java.util.ArrayList;
import java.util.List;

/**
 * @author cheng
 *         2018/9/27 12:42
 */
public class ResourceInfo {

    /**
     * 资源 ID
     */
    private Long id;

    /**
     * 资源 父ID
     */
    private Long parentId;

    /**
     * 资源名
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
    private ResourceType type;

    /**
     * 子节点
     */
    private List<ResourceInfo> children = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
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

    public List<ResourceInfo> getChildren() {
        return children;
    }

    public void setChildren(List<ResourceInfo> children) {
        this.children = children;
    }
}
