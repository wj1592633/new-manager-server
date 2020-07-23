package com.wj.manager.security.phone;

import org.springframework.security.core.userdetails.UserDetails;

@FunctionalInterface
public interface SmsAuthenticationCheck {
    public void check(UserDetails user);
}
