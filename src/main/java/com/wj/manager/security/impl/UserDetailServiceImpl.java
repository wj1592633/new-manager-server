package com.wj.manager.security.impl;

import com.wj.manager.security.constant.SecurityConstant;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailServiceImpl implements UserDetailsService{
    @Autowired
    LoginMethodManager methodManager;

    /**
     *
     * @param username
     *        必须在前端用户输入的账号后面拼接type=? ,目前支持username、phone两种，即账号登陆和手机登陆,
     *        如果想额外添加登陆方式，比如邮箱登陆，则需要在UserService添加[根据邮箱查询用户的方法]，并且
     *        在base.security.login.mapping中添加key-value键值对，key为前端传的type，value为UserService新增的方法名
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String type = StringUtils.substringAfterLast(username, SecurityConstant.LOGIN_TYPE_SEPARATOR);
        String arg = StringUtils.substringBeforeLast(username, SecurityConstant.LOGIN_TYPE_SEPARATOR);
        UserDetails userDetails = methodManager.loadUserByLoginType(arg, type);
       return userDetails;
    }
}
