package com.cheng.security.browser.session;

import com.cheng.security.core.properties.SecurityProperties;
import com.cheng.security.core.support.SimpleResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.util.UrlUtils;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 抽象的 session 失效处理器
 *
 * @author cheng
 *         2018/8/15 22:22
 */
public class AbstractSessionStrategy {

    private static final String SUFFIX = ".html";

    private final Logger logger = LoggerFactory.getLogger(AbstractSessionStrategy.class);

    /**
     * 跳转的 url
     */
    private String destinationUrl;

    /**
     * 系统配置信息
     */
    private SecurityProperties securityProperties;

    /**
     * 重定向策略
     */
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    /**
     * 跳转前是否创建新的 session
     */
    private boolean createNewSession = true;

    private ObjectMapper objectMapper = new ObjectMapper();

    public AbstractSessionStrategy(SecurityProperties securityProperties) {

        String invalidSessionUrl = securityProperties.getBrowser().getSession().getSessionInvalidUrl();

        Assert.isTrue(UrlUtils.isValidRedirectUrl(invalidSessionUrl), "url must start with '/' or with 'http(s)' ");
        Assert.isTrue(StringUtils.endsWithIgnoreCase(invalidSessionUrl, SUFFIX), "url must end with '.html'");

        this.destinationUrl = invalidSessionUrl;
        this.securityProperties = securityProperties;
    }

    protected void onSessionInvalid(HttpServletRequest request, HttpServletResponse response) throws IOException {

        logger.info("session失效");

        if (createNewSession) {
            request.getSession();
        }

        String sourceUrl = request.getRequestURI();
        String targetUrl;

        if (StringUtils.endsWithIgnoreCase(sourceUrl, SUFFIX)) {
            if (StringUtils.equals(sourceUrl, securityProperties.getBrowser().getSignInPage())
                    || StringUtils.equals(sourceUrl, securityProperties.getBrowser().getSignOutUrl())) {
                targetUrl = sourceUrl;
            } else {
                targetUrl = destinationUrl;
            }

            logger.info("跳转到: " + targetUrl);
            redirectStrategy.sendRedirect(request, response, targetUrl);
        } else {
            Object result = buildResponseContent(request);
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(objectMapper.writeValueAsString(result));
        }
    }

    protected  Object buildResponseContent(HttpServletRequest request) {

        String message = "session已失效";
        if (isConcurrency()) {
            message += "，有可能是并发登录导致的";
        }

        return new SimpleResponse(message);
    }

    /**
     * session 失效是否是并发导致的
     *
     * @return
     */
    protected boolean isConcurrency() {
        return false;
    }

    public void setCreateNewSession(boolean createNewSession) {
        this.createNewSession = createNewSession;
    }
}
