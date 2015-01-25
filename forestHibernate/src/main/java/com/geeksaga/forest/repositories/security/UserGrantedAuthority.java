package com.geeksaga.forest.repositories.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;

/**
 * @author geeksaga
 * @version 0.1
 */
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