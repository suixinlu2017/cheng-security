package com.cheng.security.rbac.repository.support;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * 包装用于构建 JPA 动态查询时所需的对象
 *
 * @author cheng
 *         2018/9/27 13:46
 */
public class QueryWrapper<T> {

    /**
     * JPA Root
     */
    private Root<T> root;

    /**
     * JPA CriteriaBuilder
     */
    private CriteriaBuilder cb;

    /**
     * JPA Predicate 集合
     */
    private List<Predicate> predicates;

    /**
     * JPA 查询对象
     */
    private CriteriaQuery<?> query;

    public QueryWrapper(Root<T> root, CriteriaBuilder cb, List<Predicate> predicates, CriteriaQuery<?> query) {
        this.root = root;
        this.cb = cb;
        this.predicates = predicates;
        this.query = query;
    }

    /**
     * 数据库查询语句连接词添加
     *
     * @param predicate
     */
    public void addPredicate(Predicate predicate) {
        this.predicates.add(predicate);
    }

    public Root<T> getRoot() {
        return root;
    }

    public void setRoot(Root<T> root) {
        this.root = root;
    }

    public CriteriaBuilder getCb() {
        return cb;
    }

    public void setCb(CriteriaBuilder cb) {
        this.cb = cb;
    }

    public List<Predicate> getPredicates() {
        return predicates;
    }

    public void setPredicates(List<Predicate> predicates) {
        this.predicates = predicates;
    }

    public CriteriaQuery<?> getQuery() {
        return query;
    }

    public void setQuery(CriteriaQuery<?> query) {
        this.query = query;
    }
}
