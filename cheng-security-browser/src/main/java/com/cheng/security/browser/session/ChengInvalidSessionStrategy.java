package com.cheng.security.browser.session;

import com.cheng.security.core.properties.SecurityProperties;
import org.springframework.security.web.session.InvalidSessionStrategy;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 默认 session 失效处理策略
 *
 * @author cheng
 *         2018/8/15 21:58
 */
public class ChengInvalidSessionStrategy extends AbstractSessionStrategy implements InvalidSessionStrategy {


    public ChengInvalidSessionStrategy(SecurityProperties securityProperties) {
        super(securityProperties);
    }

    @Override
    public void onInvalidSessionDetected(HttpServletRequest request, HttpServletResponse response) throws IOException {
        onSessionInvalid(request, response);
    }
}
