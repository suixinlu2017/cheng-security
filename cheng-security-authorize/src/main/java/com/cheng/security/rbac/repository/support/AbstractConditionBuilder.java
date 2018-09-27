package com.cheng.security.rbac.repository.support;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;

import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Collection;
import java.util.Date;

/**
 * @author cheng
 *         2018/9/27 13:44
 */
public abstract class AbstractConditionBuilder<T> {

    /**
     * 添加 in 条件
     *
     * @param queryWrapper
     * @param column
     * @param values
     */
    protected void addInConditionToColumn(QueryWrapper<T> queryWrapper, String column, Object values) {

        if (needAddCondition(values)) {
            Path<?> fieldPath = getPath(queryWrapper.getRoot(), column);
            if (values.getClass().isArray()) {
                queryWrapper.addPredicate(fieldPath.in((Object[]) values));
            } else if (values instanceof Collection) {
                queryWrapper.addPredicate(fieldPath.in((Collection<?>) values));
            }
        }
    }

    /**
     * 添加 between 条件
     *
     * @param queryWrapper
     * @param column
     * @param minValue     范围下限
     * @param maxValue     范围上限
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    protected void addBetweenConditionToColumn(QueryWrapper<T> queryWrapper, String column,
                                               Comparable minValue, Comparable maxValue) {

        if (minValue != null || maxValue != null) {
            Path<? extends Comparable> fieldPath = getPath(queryWrapper.getRoot(), column);
            if (minValue != null && maxValue != null) {
                queryWrapper.addPredicate(queryWrapper.getCb().between(fieldPath, minValue, processMaxValueOnDate(maxValue)));
            } else if (minValue != null) {
                queryWrapper.addPredicate(queryWrapper.getCb().greaterThanOrEqualTo(fieldPath, minValue));
            } else if (maxValue != null) {
                queryWrapper.addPredicate(queryWrapper.getCb().lessThanOrEqualTo(fieldPath, processMaxValueOnDate(maxValue)));
            }
        }
    }

    /**
     * 添加 大于 条件
     *
     * @param queryWrapper
     * @param column
     * @param minValue
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    protected void addGreaterThanConditionToColumn(QueryWrapper<T> queryWrapper, String column, Comparable minValue) {
        if (minValue != null) {
            Path<? extends Comparable> fieldPath = getPath(queryWrapper.getRoot(), column);
            queryWrapper.addPredicate(queryWrapper.getCb().greaterThan(fieldPath, minValue));
        }
    }

    /**
     * 添加 大于等于 条件
     *
     * @param queryWrapper
     * @param column
     * @param minValue
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    protected void addGreaterThanOrEqualConditionToColumn(QueryWrapper<T> queryWrapper, String column, Comparable minValue) {
        if (minValue != null) {
            Path<? extends Comparable> fieldPath = getPath(queryWrapper.getRoot(), column);
            queryWrapper.addPredicate(queryWrapper.getCb().greaterThanOrEqualTo(fieldPath, minValue));
        }
    }

    /**
     * 添加 小于 条件
     *
     * @param queryWrapper
     * @param column
     * @param maxValue
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    protected void addLessThanConditionToColumn(QueryWrapper<T> queryWrapper, String column, Comparable maxValue) {

        if (maxValue != null) {
            Path<? extends Comparable> fieldPath = getPath(queryWrapper.getRoot(), column);
            queryWrapper.addPredicate(queryWrapper.getCb().lessThan(fieldPath, processMaxValueOnDate(maxValue)));
        }
    }

    /**
     * 添加 小于等于 条件
     *
     * @param queryWrapper
     * @param column
     * @param maxValue
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    protected void addLessThanOrEqualConditionToColumn(QueryWrapper<T> queryWrapper, String column, Comparable maxValue) {

        if (maxValue != null) {
            Path<? extends Comparable> fieldPath = getPath(queryWrapper.getRoot(), column);
            queryWrapper.addPredicate(queryWrapper.getCb().lessThanOrEqualTo(fieldPath, processMaxValueOnDate(maxValue)));
        }
    }

    /**
     * 添加 like 条件
     *
     * @param queryWrapper
     * @param column
     * @param value
     */
    protected void addLikeConditionToColumn(QueryWrapper<T> queryWrapper, String column, String value) {
        if (StringUtils.isNotBlank(value)) {
            queryWrapper.addPredicate(createLikeCondition(queryWrapper, column, value));
        }
    }

    /**
     * 添加 like 条件
     *
     * @param queryWrapper
     * @param column
     * @param value
     */
    @SuppressWarnings("unchecked")
    protected void addStartsWidthConditionToColumn(QueryWrapper<T> queryWrapper, String column, String value) {
        if (StringUtils.isNotBlank(value)) {
            Path<String> fieldPath = getPath(queryWrapper.getRoot(), column);
            queryWrapper.addPredicate(queryWrapper.getCb().like(fieldPath, value + "%"));
        }
    }

    /**
     * 添加 等于 条件
     *
     * @param queryWrapper
     * @param column       指出要向哪个字段添加条件
     * @param value        指定字段的值
     */
    protected void addEqualsConditionToColumn(QueryWrapper<T> queryWrapper, String column, Object value) {
        if (needAddCondition(value)) {
            Path<?> fieldPath = getPath(queryWrapper.getRoot(), column);
            queryWrapper.addPredicate(queryWrapper.getCb().equal(fieldPath, value));
        }
    }

    /**
     * 添加 不等于 条件
     *
     * @param queryWrapper
     * @param column       指出要向哪个字段添加条件
     * @param value        指定字段的值
     */
    protected void addNotEqualsConditionToColumn(QueryWrapper<T> queryWrapper, String column, Object value) {
        if (needAddCondition(value)) {
            Path<?> fieldPath = getPath(queryWrapper.getRoot(), column);
            queryWrapper.addPredicate(queryWrapper.getCb().notEqual(fieldPath, value));
        }
    }


    /**
     * 判断是否需要添加 where 条件
     *
     * @param values
     * @return
     */
    @SuppressWarnings("rawtypes")
    protected boolean needAddCondition(Object values) {

        boolean addCondition = false;
        if (values != null) {
            if (values instanceof String) {
                if (StringUtils.isNotBlank(values.toString())) {
                    addCondition = true;
                }
            } else if (values.getClass().isArray()) {
                if (ArrayUtils.isNotEmpty((Object[]) values)) {
                    addCondition = true;
                }
            } else if (values instanceof Collection) {
                if (CollectionUtils.isNotEmpty((Collection) values)) {
                    addCondition = true;
                }
            } else {
                // FIXME 这里是否应该是 false ?
                addCondition = true;
            }
        }

        return addCondition;
    }

    /**
     * 获取路径
     *
     * @param root
     * @param property
     * @return
     */
    @SuppressWarnings("rawtypes")
    protected Path getPath(Root<T> root, String property) {

        String[] names = StringUtils.split(property, ".");
        Path path = root.get(names[0]);
        for (int i = 1; i < names.length; i++) {
            path = path.get(names[i]);
        }

        return path;
    }

    /**
     * 当范围的条件是小于，并且值的类型是 Date 时，将传入的 Date 值变为当天的夜里 12点的值
     *
     * @param maxValue
     * @return
     */
    private Comparable processMaxValueOnDate(Comparable maxValue) {

        if (maxValue instanceof Date) {
            maxValue = new DateTime(maxValue).withTimeAtStartOfDay().plusDays(1).plusSeconds(-1).toDate();
        }

        return maxValue;
    }

    /**
     * 创建 连接 条件
     *
     * @param queryWrapper
     * @param column
     * @param value
     * @return
     */
    @SuppressWarnings("unchecked")
    protected Predicate createLikeCondition(QueryWrapper<T> queryWrapper, String column, String value) {

        Path<String> fieldPath = getPath(queryWrapper.getRoot(), column);
        return queryWrapper.getCb().like(fieldPath, "%" + value + "%");
    }
}
