package com.wj.manager.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wj.manager.properties.SecurityProperties;
import com.wj.manager.util.ServletUtil;
import com.wj.manager.vo.ResponseResultHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * security登陆失败处理器
 */
@Component
@Slf4j
public class BaseAuthenticationFailHandler extends SimpleUrlAuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse,
                                        AuthenticationException e) throws IOException, ServletException {
        log.info("<<<---->>>登陆失败");
        //向前端返回json数据
        ServletUtil.writeJsonData(httpServletRequest,
                httpServletResponse,
                ResponseResultHelper.buildFailResult(e.getMessage()));
        return;

    }
}
