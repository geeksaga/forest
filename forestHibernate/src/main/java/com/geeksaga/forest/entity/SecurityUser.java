/*
 * GeekSaga Class Infomation Library v0.0.1
 * 
 * http://geeksaga.com/
 * 
 * Copyright 2014 GeekSaga Foundation, Inc. and other contributors
 * 
 * Released under the MIT license http://geeksaga.com/license
 */

/**
 * @author geeksaga
 * @version 0.1
 */
package com.geeksaga.forest.entity;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class SecurityUser extends User implements UserDetails
{
    private static final long serialVersionUID = 1L;

    private Collection<GrantedAuthority> authorities;

    public SecurityUser()
    {}

    public SecurityUser(User user)
    {
        if (user != null)
        {
            this.setSid(user.getSid());
            this.setEmail(user.getEmail());
            this.setPassword(user.getPassword());
            this.setFirstName(user.getFirstName());
            this.setLastName(user.getLastName());
            this.setRoles(user.getRoles());
        }
    }

    public SecurityUser(String email, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired,
            boolean accountNonLocked, Collection<GrantedAuthority> authorities) throws IllegalArgumentException
    {
        setEmail(email);
        setPassword(password);
        setEnabled(enabled);
        setAccountNonExpired(accountNonLocked);
        setCredentialsNonExpired(credentialsNonExpired);
        setAccountNonLocked(accountNonLocked);
        setAuthorities(authorities);
    }

//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities()
//    {
//        Collection<GrantedAuthority> authorities = new ArrayList<>();
//        Set<Role> userRoles = this.getRoles();
//        if (userRoles != null)
//        {
//            for (Role role : userRoles)
//            {
//                SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.getRoleName());
//                authorities.add(authority);
//            }
//        }
//
//        return authorities;
//    }

    public boolean existsAuthorities(String auth)
    {
        for (GrantedAuthority authority : getAuthorities())
        {
            if (authority.getAuthority().equals(auth))
            {
                return true;
            }
        }

        return false;
    }

    @Override
    public String getPassword()
    {
        return super.getPassword();
    }

    @Override
    public String getUsername()
    {
        return super.getEmail();
    }

    public Collection<GrantedAuthority> getAuthorities()
    {
        if (authorities == null)
        {
            return new ArrayList<GrantedAuthority>();
        }

        return authorities;
    }
    
    public void setAuthorities(Collection<GrantedAuthority> authorities)
    {
        this.authorities = authorities;
    }
}