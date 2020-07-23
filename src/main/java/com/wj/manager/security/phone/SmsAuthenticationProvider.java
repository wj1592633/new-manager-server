package com.wj.manager.security.phone;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.util.Assert;

public class SmsAuthenticationProvider implements AuthenticationProvider {

    private UserDetailsService userDetailsService;
    private SmsAuthenticationCheck postAuthenticationCheck = user -> {

    };
    private SmsAuthenticationCheck preAuthenticationCheck = user -> {
    };


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Assert.isInstanceOf(SmsAuthenticationToken.class, authentication, () -> {
            return "Only SmsAuthenticationToken is supported -->>>只支持SmsAuthenticationToken";
        });
        String phone = (String) authentication.getPrincipal();
        UserDetails user = userDetailsService.loadUserByUsername(phone);
        if (user == null) {
            throw new InternalAuthenticationServiceException("无法获取用户信息");
        }
        try {
            //前置校验
            this.preAuthenticationCheck.check(user);
        } catch (AuthenticationException var7) {
            throw var7;
        }
        //后置校验
        this.postAuthenticationCheck.check(user);
        SmsAuthenticationToken authenticationToken = new SmsAuthenticationToken(user, user.getAuthorities());

        authenticationToken.setDetails(authentication.getDetails());

        return authenticationToken;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return SmsAuthenticationToken.class.isAssignableFrom(aClass);
    }

    public UserDetailsService getUserDetailsService() {
        return userDetailsService;
    }

    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    public SmsAuthenticationCheck getPostAuthenticationCheck() {
        return postAuthenticationCheck;
    }

    public void setPostAuthenticationCheck(SmsAuthenticationCheck postAuthenticationCheck) {
        this.postAuthenticationCheck = postAuthenticationCheck;
    }

    public SmsAuthenticationCheck getPreAuthenticationCheck() {
        return preAuthenticationCheck;
    }

    public void setPreAuthenticationCheck(SmsAuthenticationCheck preAuthenticationCheck) {
        this.preAuthenticationCheck = preAuthenticationCheck;
    }
}
