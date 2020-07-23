package com.wj.manager.security.entity;

import com.wj.manager.business.entity.User;
import com.wj.manager.security.constant.SecurityConstant;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.util.*;

public class UserEx extends User implements UserDetails{

    private final Set<GrantedAuthority> authorities;
    public UserEx(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        if (username != null && !"".equals(username) && password != null) {
            this.setAccount(username);
            this.setPassword(password);
            this.authorities = Collections.unmodifiableSet(sortAuthorities(authorities));
        } else {
            throw new IllegalArgumentException(SecurityConstant.LOGIN_USERNAME_PASSWORD_ERROR_MSG);
        }
    }

    public Collection<GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    public String getUsername() {
        return this.getAccount();
    }



    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    private static SortedSet<GrantedAuthority> sortAuthorities(Collection<? extends GrantedAuthority> authorities) {
        Assert.notNull(authorities, "Cannot pass a null GrantedAuthority collection");
        SortedSet<GrantedAuthority> sortedAuthorities = new TreeSet(new UserEx.AuthorityComparator());
        Iterator var2 = authorities.iterator();

        while(var2.hasNext()) {
            GrantedAuthority grantedAuthority = (GrantedAuthority)var2.next();
            Assert.notNull(grantedAuthority, "GrantedAuthority list cannot contain any null elements");
            sortedAuthorities.add(grantedAuthority);
        }

        return sortedAuthorities;
    }

    public boolean equals(Object rhs) {
        return rhs instanceof UserEx ? this.getUsername().equals(((UserEx )rhs).getUsername()) : false;
    }

    public int hashCode() {
        return this.getUsername().hashCode();
    }
    private static class AuthorityComparator implements Comparator<GrantedAuthority>, Serializable {
        private static final long serialVersionUID = 520L;

        private AuthorityComparator() {
        }

        public int compare(GrantedAuthority g1, GrantedAuthority g2) {
            if (g2.getAuthority() == null) {
                return -1;
            } else {
                return g1.getAuthority() == null ? 1 : g1.getAuthority().compareTo(g2.getAuthority());
            }
        }
    }
}