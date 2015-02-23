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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.geeksaga.forest.common.util.MessageUtils;
import com.geeksaga.forest.entity.SecurityUser;

@Service
public class CustomAuthenticationProvider implements AuthenticationProvider
{
    private static final Logger logger = LoggerFactory.getLogger(CustomAuthenticationProvider.class);

    @Autowired
    private CustomUserDetailService userService;

    @Autowired
    private MessageSource messageSource;
    
    @Autowired
    private MessageUtils messageUtils;
    
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException
    {
        String username = authentication.getName();
        String password = (String) authentication.getCredentials();

        SecurityUser user;

        try
        {
            user = (SecurityUser) userService.loadUserByUsername(username);

            if (user != null && user.getPassword() == null)
            {
                throw new UsernameNotFoundException(messageUtils.getMessage("forest.msg.usernameNotFound"));
            }

            if (user != null && !passwordEncoder.matches(password, user.getPassword()))
            {
                throw new BadCredentialsException(messageUtils.getMessage("forest.msg.badCredentials"));
            }
        }
        catch (UsernameNotFoundException e)
        {
            throw e;
        }
        catch (BadCredentialsException e)
        {
            throw e;
        }
        catch (Exception e)
        {
            throw new AuthenticationServiceException(e.getMessage());
        }

        return new UsernamePasswordAuthenticationToken(user, password, user.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> arg0)
    {
        return true;
    }
}