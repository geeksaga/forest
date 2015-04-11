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
package com.geeksaga.forest.service;

import org.springframework.dao.DataAccessException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import com.geeksaga.forest.entity.SecurityUser;

@Service
public class UserAuthenticationService implements AuthenticationManager
{
    private SecurityUser user;

    /**
     * @param authentication
     * @param user
     * @return
     * @throws DataAccessException
     */
    public Authentication authenticate(Authentication authentication, SecurityUser user) throws DataAccessException
    {
        this.user = user;

        return authenticate(authentication);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.security.authentication.AuthenticationManager#authenticate(org.springframework.security.core.Authentication)
     */
    public Authentication authenticate(Authentication authentication) throws AuthenticationException
    {
        if (user != null)
        {
            return new UsernamePasswordAuthenticationToken(user, authentication.getCredentials(), user.getAuthorities());
        }
        else
        {
            throw new BadCredentialsException("Bad Credentials");
        }
    }
}