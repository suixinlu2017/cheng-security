package com.cheng.security.rbac.repository.support;


import org.springframework.core.convert.converter.Converter;

/**
 * @author cheng
 *         2018/9/27 14:26
 */
public interface Domain2InfoConverter<T, I> extends Converter<T, I> {
}
