package com.cheng.security.app.social;

import com.cheng.security.app.AppSecretException;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionData;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.concurrent.TimeUnit;

/**
 * app环境下替换 providerSignInUtils，避免由于没有 session 导致读不到社交用户信息的问题
 *
 * @author cheng
 *         2018/08/30 15:07
 */
@Component
public class AppSignUpUtils {

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    @Autowired
    private UsersConnectionRepository usersConnectionRepository;

    @Autowired
    private ConnectionFactoryLocator connectionFactoryLocator;

    /**
     * 缓存社交网站用户信息到 redis
     */
    public void saveConnectionData(WebRequest request, ConnectionData connectionData) {
        redisTemplate.opsForValue().set(getKey(request), connectionData, 10, TimeUnit.MINUTES);
    }

    /**
     * 将缓存的社交网站用户信息与系统注册用户信息绑定
     *
     * @param request
     * @param userId
     */
    public void doPostSignUp(WebRequest request, String userId) {

        String key = getKey(request);
        if (!redisTemplate.hasKey(key)) {
            throw new AppSecretException("无法找到缓存的用户社交帐号信息");
        }

        // 根据 key 的值从 redis 中拿到 ConnectionData
        ConnectionData connectionData = (ConnectionData) redisTemplate.opsForValue().get(key);
        // 通过 providerId 用 connectionFactoryLocator 拿到连接工厂，再根据 ConnectionData 创建 connection
        Connection<?> connection = connectionFactoryLocator.getConnectionFactory(connectionData.getProviderId())
                .createConnection(connectionData);
        // 根据 userId 拿到这个用户对应的 usersConnectionRepository，然后与 connection 绑定
        usersConnectionRepository.createConnectionRepository(userId).addConnection(connection);

        redisTemplate.delete(key);
    }

    /**
     * 获取 redis key
     *
     * @param request
     * @return
     */
    private String getKey(WebRequest request) {

        String deviceId = request.getHeader("deviceId");
        if (StringUtils.isBlank(deviceId)) {
            throw new AppSecretException("设备id参数不能为空");
        }

        return "cheng:security:social.connect." + deviceId;
    }
}
