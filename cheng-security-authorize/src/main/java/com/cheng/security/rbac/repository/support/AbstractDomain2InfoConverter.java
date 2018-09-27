package com.cheng.security.rbac.repository.support;


import org.springframework.beans.BeanUtils;

/**
 * @author cheng
 *         2018/9/27 14:25
 */
public abstract class AbstractDomain2InfoConverter<T, I> implements Domain2InfoConverter<T, I> {

    @SuppressWarnings("unchecked")
    @Override
    public I convert(T domain) {
        I info = null;
        try {
            Class<I> clazz = GenericUtils.getGenericClass(getClass(), 1);
            info = clazz.newInstance();
            BeanUtils.copyProperties(domain, info);
            doConvert(domain, info);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return info;
    }

    /**
     * domain 类型具体转换
     *
     * @param domain
     * @param info
     * @throws Exception
     */
    protected abstract void doConvert(T domain, I info) throws Exception;
}
