package com.cheng.security.app.social;

import com.cheng.security.core.properties.SecurityConstants;
import com.cheng.security.core.social.support.ChengSpringSocialConfigurer;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * 针对app环境的处理，在请求 chengSocialSecurityConfig 的跳到社交登录页
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
            configurer.signupUrl(SecurityConstants.DEFAULT_SOCIAL_USER_INFO_URL);
            return configurer;
        }
        return bean;
    }
}
