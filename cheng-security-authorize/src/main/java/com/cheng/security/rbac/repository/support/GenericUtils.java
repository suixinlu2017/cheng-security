package com.cheng.security.rbac.repository.support;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * 泛型工具
 *
 * @author cheng
 *         2018/9/27 13:43
 */
public class GenericUtils {

    private GenericUtils() {
    }

    /**
     * 获取目标 class 的第一个泛型参数的类型
     *
     * @param clazz
     * @return
     */
    public static Class getGenericClass(Class clazz) {
        return getGenericClass(clazz, 0);
    }

    /**
     * 获取目标 class 的指定位置的泛型参数的类型
     *
     * @param clazz
     * @param index 泛型参数的位置，第一个参数为 0
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static Class getGenericClass(Class clazz, int index) {

        Type type = clazz.getGenericSuperclass();
        if (type instanceof ParameterizedType) {
            Type[] params = ((ParameterizedType) type).getActualTypeArguments();
            if (params[index] instanceof ParameterizedType) {
                return ((ParameterizedType) params[index]).getRawType().getClass();
            } else {
                return (Class) params[index];
            }
        }

        throw new RuntimeException("无法获得泛型的类型");
    }
}
