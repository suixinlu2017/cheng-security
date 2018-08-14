package com.cheng.security.core.social.wechat.config;

import com.cheng.security.core.properties.SecurityProperties;
import com.cheng.security.core.properties.WechatProperties;
import com.cheng.security.core.social.social.ChengConnectView;
import com.cheng.security.core.social.wechat.connect.WechatConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.ConnectionFactory;
import org.springframework.web.servlet.View;

/**
 * 微信登录配置
 *
 * @author cheng
 *         2018/8/13 21:33
 */
@Configuration
@ConditionalOnProperty(prefix = "cheng.security.social.wechat", name = "app-id")
public class WechatAutoConfiguration extends SocialAutoConfigurerAdapter {


    @Autowired
    private SecurityProperties securityProperties;

    @Override
    protected ConnectionFactory<?> createConnectionFactory() {

        WechatProperties wechatConfig = securityProperties.getSocial().getWechat();
        return new WechatConnectionFactory(wechatConfig.getProviderId(), wechatConfig.getAppId(),
                wechatConfig.getAppSecret());
    }


    @Bean({"connect/wechatConnect", "connect/wechatConnected"})
    @ConditionalOnMissingBean(name = "wechatConnectedView")
    public View wechatConnectedView() {
        return new ChengConnectView();
    }
}
