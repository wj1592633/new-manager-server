package com.wj.manager.filter;

import com.wj.manager.properties.JwtProperties;
import com.wj.manager.security.constant.SecurityConstant;
import com.wj.manager.security.entity.UserEx;
import com.wj.manager.system.jwt.JwtHelper;
import com.wj.manager.util.ServletUtil;
import com.wj.manager.vo.ResponseResultHelper;
import io.jsonwebtoken.Claims;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * jwt过滤器，校验jwt
 */
public class JWTFilter extends OncePerRequestFilter {
    private AntPathMatcher pathMatcher;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String requestURI = httpServletRequest.getRequestURI();
        //判断当前路径是否要进行token校验。如果当前路径匹配security中忽略的路径，则flag = true，
        // 即不用校验，直接放行
        boolean flag = assertUri(SecurityConstant.IGNORE_URL, requestURI);
        //如果当前路径匹配security中允许的路径，则flag = true，
        // 即不用校验，直接放行
        if (!flag) {
            flag = assertUri(SecurityConstant.PERMIT_URL, requestURI);
        }
        //需要校验，则解析jwt，解析完后放行
        if (!flag) {
            String authrization = httpServletRequest.getHeader("Authorization");
            if (authrization != null) {
                Claims claims = JwtHelper.parseJwtIgnoreExpire(authrization, "1111111fas1111111111da");
                Authentication authentication = claims2Authentication(claims);
                //verify(claims, authentication, httpServletRequest, httpServletResponse);
                Date expiration = claims.getExpiration();
                //当生成token时时间设置为负数，则表示token永久有效
                //不为负数则expiration不为null，要进行校验是否过期
                if (expiration != null) {
                    Date now = new Date();
                    boolean isExpire = now.after(expiration);
                    //如果过期
                    if (isExpire) {
                        Date refreshTime = claims.get(JwtProperties.REFRESH, Date.class);
                        boolean canRefresh = now.before(refreshTime);
                        //是否能刷新过期的token
                        if (canRefresh) {
                            //todo
                            String token = JwtHelper.createJWT(authentication, 60 * 3000L, JwtProperties.signKey);
                            ResponseResultHelper.withTokenData(JwtProperties.CODE_REFRESH, token);
                        } else {
                            //不能刷新则让重新登陆
                            ServletUtil.writeJsonData(httpServletRequest, httpServletResponse, ResponseResultHelper.build401Result(SecurityConstant.LOGIN_AGAIN_MSG));
                            return;
                        }
                    }
                }
                SecurityContext context = new SecurityContextImpl(authentication);
                SecurityContextHolder.setContext(context);
            } else {
                ServletUtil.writeJsonData(httpServletRequest, httpServletResponse, ResponseResultHelper.build401Result(SecurityConstant.NEED_LOGIN_MSG));
                return;
            }
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    //todo
    private Authentication claims2Authentication(Claims claims) {
        String id = claims.get(JwtProperties.ID, String.class);
        String auth = claims.get(JwtProperties.AUTHORITIES, String.class);
        String username = claims.get(JwtProperties.USER_NAME, String.class);
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList(auth);
        UserEx userEx = new UserEx(username, "", grantedAuthorities);
        return new UsernamePasswordAuthenticationToken(userEx, null, grantedAuthorities);
    }

    private boolean assertUri(String[] patterns, String uri) {
        for (String pattern : patterns) {
            if (pathMatcher.match(pattern, uri)) {
                return true;
            }
        }
        return false;
    }

    public AntPathMatcher getPathMatcher() {
        return pathMatcher;
    }

    public void setPathMatcher(AntPathMatcher pathMatcher) {
        this.pathMatcher = pathMatcher;
    }
}
