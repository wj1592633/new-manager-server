package com.wj.manager.business.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wj.manager.business.entity.User;
import com.wj.manager.business.mapper.UserMapper;
import com.wj.manager.business.service.UserService;
import com.wj.manager.security.constant.SecurityConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 系统用户表 服务实现类
 * </p>
 *
 * @author wj
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Override
    public User getRecordByUsername(String account) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("account",account);
        User user = baseMapper.selectOne(wrapper);
        if (user == null){
            throw new BadCredentialsException(SecurityConstant.LOGIN_USERNAME_PASSWORD_ERROR_MSG);
        }
        return user;
    }

    @Override
    public User getRecordByPhone(String phone) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("phone",phone);
        User user = baseMapper.selectOne(wrapper);
        if (user == null){
            throw new BadCredentialsException(SecurityConstant.LOGIN_PHONE_ERROR_MSG);
        }
        return user;
    }
}
