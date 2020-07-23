package com.wj.manager.filter;

import com.wj.manager.exception.ValidateCodeException;
import com.wj.manager.properties.SecurityProperties;
import com.wj.manager.security.constant.SecurityConstant;
import com.wj.manager.util.KeyUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * 短信验证码拦截器，拦截请求并校验验证码
 *
 * 逻辑和ValidateCodeFilter一样，可以不加此过滤器，直接用ValidateCodeFilter来
 * 进行图片验证码和短信验证码的校验(毕竟都是校验字符串)
 *
 * 有特殊处理时，当然也加上此过滤器分开来校验
 */
public class SmsCodeFilter extends OncePerRequestFilter implements InitializingBean{
    AuthenticationFailureHandler failHandler;
    Set<String> urlSet = new HashSet<>();
    SecurityProperties securityProperties;
    AntPathMatcher pathMatcher = null;

    @Override
    public void afterPropertiesSet() throws ServletException {
        super.afterPropertiesSet();
        securityProperties.getCode().getValidateUrl();
        String[] urls = StringUtils.splitByWholeSeparatorPreserveAllTokens(securityProperties.getCode().getValidateUrl(), ",");
       for (String e : urls){
           urlSet.add(e);
       }
       urlSet.add(SecurityConstant.LOGIN_PROCESSING_URL);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        boolean flag = false;
        for (String var1 : urlSet){
            if (pathMatcher.match(var1, httpServletRequest.getRequestURI())){
                flag = true;
                break;
            }
        }
        if ("/login/phone".equals(httpServletRequest.getRequestURI())) {
            String phone = httpServletRequest.getParameter("phone");
            String smsValidateCodeKey = KeyUtil.getSmsValidateCodeKey(StringUtils.substringBeforeLast(phone,"type="));
            // 获取session中的验证码
            String sessionCode = (String) httpServletRequest.getSession().getAttribute(smsValidateCodeKey);
            if (sessionCode == null ) {
                failHandler.onAuthenticationFailure(httpServletRequest,
                        httpServletResponse,
                        new ValidateCodeException("验证码已经失效"));
                return;
            }
            String verCode = httpServletRequest.getParameter("verCode");
            // 判断验证码
            if (StringUtils.equalsIgnoreCase(verCode, sessionCode)) {
                filterChain.doFilter(httpServletRequest, httpServletResponse);
            } else {
                failHandler.onAuthenticationFailure(httpServletRequest,
                        httpServletResponse,
                        new ValidateCodeException("验证码错误"));
                return;
            }
        } else {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        }
    }

    public SecurityProperties getSecurityProperties() {
        return securityProperties;
    }

    public void setSecurityProperties(SecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }

    public AuthenticationFailureHandler getFailHandler() {
        return failHandler;
    }

    public void setFailHandler(AuthenticationFailureHandler failHandler) {
        this.failHandler = failHandler;
    }

    public AntPathMatcher getPathMatcher() {
        return pathMatcher;
    }

    public void setPathMatcher(AntPathMatcher pathMatcher) {
        this.pathMatcher = pathMatcher;
    }
}
