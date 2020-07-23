package com.wj.manager.security.impl;

import com.wj.manager.business.entity.User;
import com.wj.manager.business.service.UserService;
import com.wj.manager.properties.SecurityProperties;
import com.wj.manager.security.constant.SecurityConstant;
import com.wj.manager.security.entity.UserEx;
import com.wj.manager.vo.ResponseResultHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Slf4j
@Component
public class LoginMethodManager {

    @Autowired
    UserService userService;
    @Autowired
    SecurityProperties securityProperties;

    public UserDetails loadUserByLoginType(String arg, String type) {
        Method method = getMethod(type);
        if (method != null) {
            try {
                method.setAccessible(true);
                //userService 查出用户
                User invoke = (User) method.invoke(userService, arg);
                UserEx user = new UserEx(invoke.getAccount(), invoke.getPassword(), AuthorityUtils.commaSeparatedStringToAuthorityList("/aaa,/bbb"));
                invoke.setPassword("");
                //把用户放进threadLocal，用于前端显示用户信息
                ResponseResultHelper.pushLoginUser(invoke);
                return user;
            } catch (AuthenticationException e) {
                throw e;
            } catch (InvocationTargetException e) {
                Throwable exception = e.getTargetException();
                if (AuthenticationException.class.isAssignableFrom(exception.getClass())) {
                    throw (AuthenticationException) exception;
                }
            } catch (Exception e) {
                log.error("MethodManager.class 方法出错--》》》" + e.getMessage());
                //todo 提示内容要改
                throw new AuthenticationServiceException(SecurityConstant.SERVER_ERROR_MSG);
            }
        }
        throw new AuthenticationServiceException(SecurityConstant.SERVER_ERROR_MSG);
    }

    private Method getMethod(String type) {
        String methodNameStr = securityProperties.getLogin().getMapping().get(type);
        try {
            return UserService.class.getDeclaredMethod(methodNameStr, String.class);
        } catch (NoSuchMethodException e) {
            log.info(e.getMessage());
            return null;
        }
    }

}
