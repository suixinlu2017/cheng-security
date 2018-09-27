package com.cheng.security.rbac.init;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import java.util.Comparator;
import java.util.List;

/**
 * 系统初始化器
 *
 * @author cheng
 *         2018/9/27 15:41
 */
public class SystemDataInitializer implements ApplicationListener<ContextRefreshedEvent> {

    private static final Logger LOGGER = LoggerFactory.getLogger(SystemDataInitializer.class);

    /**
     * 系统中所有的 {@link DataInitializer} 接口的实现
     */
    @Autowired(required = false)
    private List<DataInitializer> dataInitializers;

    /**
     * 循环调用系统中所有的 {@link DataInitializer} 接口的实现
     *
     * @param event
     */
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        if (CollectionUtils.isNotEmpty(dataInitializers)) {
            dataInitializers.sort(Comparator.comparing(DataInitializer::getIndex));
            dataInitializers.forEach(dataInitializer -> {
                try {
                    dataInitializer.init();
                } catch (Exception e) {
                    LOGGER.error("系统数据初始化失败(" + dataInitializer.getClass().getSimpleName() + ")", e);
                }
            });
        }
    }
}
