package com.cheng.security.rbac.repository.support;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * @author cheng
 *         2018/9/27 14:54
 */
public abstract class AbstractChengSpecification<T, C> extends AbstractEventConditionBuilder<T, C>
        implements Specification<T> {

    public AbstractChengSpecification(C condition) {
        super(condition);
    }

    /**
     * 构建查询条件，子类必须实现 addCondition 方法来编写查询的逻辑
     * <p>
     * 子类可以通过 addFetch 方法控制查询的关联和抓取行为
     *
     * @param root
     * @param query
     * @param cb
     * @return
     */
    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

        if (Long.class != query.getResultType()) {
            addFetch(root);
        }

        List<Predicate> predicates = new ArrayList<>();

        QueryWrapper<T> queryWrapper = new QueryWrapper<>(root, cb, predicates, query);

        addCondition(queryWrapper);

        Predicate permissionCondition = getPermissionCondition(queryWrapper);
        if (permissionCondition != null) {
            queryWrapper.addPredicate(permissionCondition);
        }

        return cb.and(predicates.toArray(new Predicate[0]));
    }

    /**
     * 添加权限条件，如果要查询的 domain 实现了 {@link ManagedByOrgan} 接口，那么传入的 condition 对象也应该实现该接口；
     * 程序会尝试从 Condition 对象中获取 organFullId，然后作为 like 查询条件添加到查询中。
     * 查出所有以传入 organFullId 开头的 domain
     *
     * @param queryWrapper
     * @return
     */
    protected Predicate getPermissionCondition(QueryWrapper<T> queryWrapper) {
        return null;
    }

    /**
     * 子类可以通过覆盖此方法实现关联抓取，避免 n+1 查询
     *
     * @param root
     */
    protected void addFetch(Root<T> root) {
    }

    /**
     * 添加查询条件
     *
     * @param queryWrapper
     */
    protected abstract void addCondition(QueryWrapper<T> queryWrapper);
}
