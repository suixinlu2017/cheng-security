package com.cheng.security.app;

import com.cheng.security.core.social.ChengSpringSocialConfigurer;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * 针对app环境的处理，在请求 chengSocialSecurityConfig 的时候修改配置的注册页面为 /social/singUp
 *
 * @author cheng
 *         2018/08/30 15:34
 */
@Component
public class SpringSocialConfigurerPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

        if (StringUtils.equals(beanName, "chengSocialSecurityConfig")) {
            ChengSpringSocialConfigurer configurer = (ChengSpringSocialConfigurer) bean;
            configurer.signupUrl("/social/singUp");
            return configurer;
        }

        return bean;
    }
}
