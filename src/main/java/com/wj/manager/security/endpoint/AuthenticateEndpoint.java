package com.wj.manager.security.endpoint;

import com.wj.manager.vo.ResponseResultHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@Slf4j
public class AuthenticateEndpoint {
    private RequestCache requestCache = new HttpSessionRequestCache();
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @RequestMapping(value = "/authenticatiuon/require", method = RequestMethod.GET)
    public Object authenticatiuonReqire(HttpServletRequest request, HttpServletResponse response){
       return ResponseResultHelper.build401Result();
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public void handleLogout(HttpServletRequest request, HttpServletResponse response){
        //todo
        log.info("处理退出登陆--->>>>>>>>>>");
    }
}
