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
import java.util.Set;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class SecurityUser extends User implements UserDetails
{
    private static final long serialVersionUID = 1L;

    private Collection<SimpleGrantedAuthority> authorities;

    public SecurityUser()
    {}

    public SecurityUser(String email)
    {
        setEmail(email);
    }

    public SecurityUser(User user)
    {
        if (user != null)
        {
            setSid(user.getSid());
            setEmail(user.getEmail());
            setPassword(user.getPassword());
            setFirstName(user.getFirstName());
            setLastName(user.getLastName());
            setAuthority(user.getAuthority());
        }
    }

    public SecurityUser(String email, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired,
            boolean accountNonLocked, Collection<SimpleGrantedAuthority> authorities) throws IllegalArgumentException
    {
        setEmail(email);
        setPassword(password);
        setEnabled(enabled);
        setAccountNonExpired(accountNonLocked);
        setCredentialsNonExpired(credentialsNonExpired);
        setAccountNonLocked(accountNonLocked);
        setAuthorities(authorities);
    }

    public boolean existsAuthorities(String auth)
    {
        for (SimpleGrantedAuthority authority : getAuthorities())
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

    @Override
    public Collection<SimpleGrantedAuthority> getAuthorities()
    {
        if (authorities == null)
        {
            authorities = new ArrayList<SimpleGrantedAuthority>();
        }

        Set<Authority> userRoles = getAuthority();
        if (userRoles != null)
        {
            for (Authority role : userRoles)
            {
                authorities.add(new SimpleGrantedAuthority(role.getRole()));
            }
        }

        return authorities;
    }

    public void setAuthorities(Collection<SimpleGrantedAuthority> authorities)
    {
        this.authorities = authorities;
    }

    public String toString()
    {
        return "SecurityUser [sid=" + getSid() + ", firstName=" + getFirstName() + ", lastName=" + getLastName() + ", email=" + getEmail()
                + "]";
    }
}