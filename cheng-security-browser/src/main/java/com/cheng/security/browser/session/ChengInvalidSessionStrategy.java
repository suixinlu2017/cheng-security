package com.cheng.security.browser.session;

import org.springframework.security.web.session.InvalidSessionStrategy;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 并发登录导致 session 失效时，默认的处理策略
 *
 * @author cheng
 *         2018/8/15 21:58
 */
public class ChengInvalidSessionStrategy extends AbstractSessionStrategy implements InvalidSessionStrategy {


    public ChengInvalidSessionStrategy(String invalidSessionUrl) {
        super(invalidSessionUrl);
    }

    @Override
    public void onInvalidSessionDetected(HttpServletRequest request, HttpServletResponse response) throws IOException {
        onSessionInvalid(request, response);
    }
}
