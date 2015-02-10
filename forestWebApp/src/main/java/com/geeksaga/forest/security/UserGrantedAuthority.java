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
package com.geeksaga.forest.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;

@SuppressWarnings("deprecation")
public class UserGrantedAuthority extends GrantedAuthorityImpl
{
    private static final long serialVersionUID = 1L;

    private List<GrantedAuthority> parentAuthorities = new ArrayList<GrantedAuthority>();

    /**
     * 
     * @param role
     * @param businessFunctions
     */
    public UserGrantedAuthority(String role)
    {
        super(role);
    }

    /**
     * 
     * @param authority
     */
    public void addParentAuthority(GrantedAuthority authority)
    {
        parentAuthorities.add(authority);
    }

    /**
     * 
     * @return
     */
    public List<GrantedAuthority> getParentAuthorities()
    {
        return parentAuthorities;
    }
}