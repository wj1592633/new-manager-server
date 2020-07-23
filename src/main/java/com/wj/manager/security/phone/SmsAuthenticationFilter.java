package com.wj.manager.security.phone;

import org.springframework.lang.Nullable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 处理短信认证码
 */
public class SmsAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
    public static final String SPRING_SECURITY_FORM_PHONE_KEY = "phone";
    private String phoneParameter = "phone";
    private boolean postOnly = true;

    public SmsAuthenticationFilter() {
        //设置该过滤器匹配的拦截路径
        super(new AntPathRequestMatcher("/login/phone", "POST"));
    }

    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (this.postOnly && !request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        } else {
            String phone = this.obtainPhone(request);
            if (phone == null) {
                phone = "";
            }

            phone = phone.trim();
            SmsAuthenticationToken authRequest = new SmsAuthenticationToken(phone);
            this.setDetails(request, authRequest);
            AuthenticationManager authenticationManager = this.getAuthenticationManager();
            Authentication authenticate = authenticationManager.authenticate(authRequest);
            return authenticate;
        }
    }

    @Nullable
    protected String obtainPhone(HttpServletRequest request) {
        return request.getParameter(this.phoneParameter);
    }

    protected void setDetails(HttpServletRequest request, SmsAuthenticationToken authRequest) {
        authRequest.setDetails(this.authenticationDetailsSource.buildDetails(request));
    }


    public void setPhoneParameter(String phoneParameter) {
        Assert.hasText(phoneParameter, "phone parameter must not be empty or null");
        this.phoneParameter = phoneParameter;
    }

    public void setPostOnly(boolean postOnly) {
        this.postOnly = postOnly;
    }

    public String getPhoneParameter() {
        return phoneParameter;
    }
}
