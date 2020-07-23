package com.wj.manager.filter;

import com.wf.captcha.base.Captcha;
import com.wj.manager.exception.ValidateCodeException;
import com.wj.manager.properties.SecurityProperties;
import com.wj.manager.security.constant.SecurityConstant;
import com.wj.manager.util.KeyUtil;
import com.wj.manager.vercode.constant.VerCodeConstant;
import com.wj.manager.vercode.store.ValidateCodeStorer;
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
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 图片验证码拦截器，拦截请求并校验验证码
 */
public class ValidateCodeFilter extends OncePerRequestFilter implements InitializingBean {

    AuthenticationFailureHandler failHandler;
    //需要拦截并进行验证码校验的路径集合，在base.security.code.validateUrl中配置
    Set<String> urlSet = new HashSet<>();
    SecurityProperties securityProperties;
    AntPathMatcher pathMatcher = null;
    ValidateCodeStorer validateCodeStorer;

    @Override
    public void afterPropertiesSet() throws ServletException {
        super.afterPropertiesSet();
        String[] urls = StringUtils.splitByWholeSeparatorPreserveAllTokens(securityProperties.getCode().getValidateUrl(), ",");
        for (String e : urls) {
            urlSet.add(e);
        }
        urlSet.add(SecurityConstant.LOGIN_PROCESSING_URL);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        boolean flag = false;
        String requestURI = httpServletRequest.getRequestURI();
        for (String var1 : urlSet) {
            if (pathMatcher.match(var1, requestURI)) {
                flag = true;
                break;
            }
        }
        //todo  if (flag)
        if (flag) {
            String key = httpServletRequest.getParameter("key");
            // 获取服务器中的验证码
            String serverCode = validateCodeStorer.get(key, httpServletRequest, httpServletResponse);
            if (serverCode == null) {
                failHandler.onAuthenticationFailure(httpServletRequest,
                        httpServletResponse,
                        new ValidateCodeException(VerCodeConstant.ILLEGAL_VER_CODE));
                return;
            }
            //前端传来的验证码
            String verCode = httpServletRequest.getParameter("verCode");
            // 判断验证码
            if (StringUtils.equalsIgnoreCase(verCode, serverCode)) {
                //移除验证码
                validateCodeStorer.remove(key, httpServletRequest, httpServletResponse);
                filterChain.doFilter(httpServletRequest, httpServletResponse);
            } else {
                failHandler.onAuthenticationFailure(httpServletRequest,
                        httpServletResponse,
                        new ValidateCodeException(VerCodeConstant.ERROR_VER_CODE));
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

    public ValidateCodeStorer getValidateCodeStorer() {
        return validateCodeStorer;
    }

    public void setValidateCodeStorer(ValidateCodeStorer validateCodeStorer) {
        this.validateCodeStorer = validateCodeStorer;
    }
}
