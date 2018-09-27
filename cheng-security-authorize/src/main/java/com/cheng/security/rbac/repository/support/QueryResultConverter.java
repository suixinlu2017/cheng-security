package com.cheng.security.rbac.repository.support;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

/**
 * @author cheng
 *         2018/9/27 15:11
 */
public class QueryResultConverter {

    private static final Logger LOGGER = LoggerFactory.getLogger(QueryResultConverter.class);

    /**
     * @param pageData
     * @param clazz
     * @param pageable
     * @param <T>
     * @param <I>
     * @return
     */
    public static <T, I> Page<I> convert(Page<T> pageData, Class<I> clazz, Pageable pageable) {

        List<T> contents = pageData.getContent();
        List<I> infos = convert(contents, clazz);

        return new PageImpl<>(infos, pageable, pageData.getTotalElements());
    }

    public static <I, T> List<I> convert(List<T> contents, Class<I> clazz) {

        List<I> infos = new ArrayList<>();
        for (T domain : contents) {
            I info;
            try {
                info = clazz.newInstance();
                BeanUtils.copyProperties(info, domain);
            } catch (Exception e) {
                LOGGER.info("转换数据失败", e);
                throw new RuntimeException("转换数据失败");
            }
            if (info != null) {
                infos.add(info);
            }

        }
        return infos;
    }

    /**
     *
     * @param pageData
     * @param pageable
     * @param converter
     * @param <T>
     * @param <I>
     * @return
     */
    public static <T, I> Page<I> convert(Page<T> pageData, Pageable pageable, Domain2InfoConverter<T, I> converter) {

        List<T> contents = pageData.getContent();
        List<I> infos = convert(contents, converter);

        return new PageImpl<>(infos, pageable, pageData.getTotalElements());
    }

    public static <I, T> List<I> convert(List<T> contents, Domain2InfoConverter<T, I> converter) {

        List<I> infos = new ArrayList<>();
        for (T domain : contents) {
            infos.add(converter.convert(domain));
        }
        return infos;
    }
}
