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

import java.util.Collection;
import java.util.Locale;

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
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.geeksaga.forest.common.util.BundleUtils;
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

    // @Autowired
    // private SaltSource saltSource;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException
    {
        String username = authentication.getName();
        String password = (String) authentication.getCredentials();

        SecurityUser user;
        Collection<GrantedAuthority> authorities;

        try
        {
            user = (SecurityUser) userService.loadUserByUsername(username);

            String hashedPassword = passwordEncoder.encode(password);

            logger.debug("username : " + username + " / password : " + password + " / hash password : " + hashedPassword);
            logger.debug("username : " + user.getUsername() + " / password : " + user.getPassword());
            
            logger.debug(messageSource.getMessage("forest.msg.usernameNotFound", null, Locale.KOREAN));
            logger.debug(BundleUtils.getString("messages", "forest.msg.usernameNotFound"));
            logger.debug(messageUtils.toString());
            logger.debug(messageUtils.toString());
            logger.debug(messageUtils.getMessage("forest.msg.usernameNotFound"));
            
            if (user != null && user.getPassword() == null)
            {
                throw new UsernameNotFoundException(messageUtils.getMessage("forest.msg.usernameNotFound"));
            }

            if (user != null && !passwordEncoder.matches(user.getPassword(), hashedPassword))
            {
                throw new BadCredentialsException(messageUtils.getMessage("forest.msg.badCredentials"));
            }

            authorities = user.getAuthorities();
            authorities.add(new SimpleGrantedAuthority("ADMIN"));
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

        return new UsernamePasswordAuthenticationToken(user, password, authorities);
    }

    @Override
    public boolean supports(Class<?> arg0)
    {
        return true;
    }
}