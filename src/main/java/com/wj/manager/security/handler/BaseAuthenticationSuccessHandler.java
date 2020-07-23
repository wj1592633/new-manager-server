package com.wj.manager.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wj.manager.properties.JwtProperties;
import com.wj.manager.properties.SecurityProperties;
import com.wj.manager.system.jwt.JwtConstant;
import com.wj.manager.system.jwt.JwtHelper;
import com.wj.manager.util.ServletUtil;
import com.wj.manager.vo.ResponseResult;
import com.wj.manager.vo.ResponseResultHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Slf4j
public class BaseAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        log.info(authentication.getPrincipal() + "<<<---->>>登陆成功");
        String token = JwtHelper.createJWT(authentication, JwtProperties.expireTime, JwtConstant.SIGN_KEY);
        ResponseResult responseResult = ResponseResultHelper.buildLoginResult(token);
        ServletUtil.writeJsonData(httpServletRequest, httpServletResponse, responseResult);
        return;
    }
}
