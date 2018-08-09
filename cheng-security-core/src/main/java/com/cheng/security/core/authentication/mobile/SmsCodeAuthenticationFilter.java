package com.cheng.security.core.authentication.mobile;

import com.cheng.security.core.properties.SecurityConstants;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 短信登录过滤器
 * <p>
 * copy from {@link org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter}
 *
 * @author cheng
 *         2018/8/7 18:04
 */
public class SmsCodeAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    private static final String CHENG_FORM_MOBILE_KEY = "mobile";

    private String mobileParameter = CHENG_FORM_MOBILE_KEY;
    private boolean postOnly = true;


    public SmsCodeAuthenticationFilter() {
        // 处理的请求路径
        super(new AntPathRequestMatcher(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_MOBILE, "POST"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {

        if (postOnly && !"POST".equals(request.getMethod())) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }

        String mobile = obtainMobile(request);

        if (mobile == null) {
            mobile = "";
        }

        mobile = mobile.trim();

        // 实例化 Token
        SmsCodeAuthenticationToken authRequest = new SmsCodeAuthenticationToken(mobile);

        setDetails(request, authRequest);

        return this.getAuthenticationManager().authenticate(authRequest);
    }

    /**
     * 获取手机号
     *
     * @param request
     * @return
     */
    private String obtainMobile(HttpServletRequest request) {
        return request.getParameter(mobileParameter);
    }

    private void setDetails(HttpServletRequest request,
                            SmsCodeAuthenticationToken authRequest) {
        authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
    }

    public void setPostOnly(boolean postOnly) {
        this.postOnly = postOnly;
    }

    public final String getMobileParameter() {
        return mobileParameter;
    }

    public void setMobileParameter(String mobileParameter) {
        Assert.hasText(mobileParameter, "Mobile parameter must not be empty or null");
        this.mobileParameter = mobileParameter;
    }
}