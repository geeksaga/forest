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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
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

import com.geeksaga.forest.repositories.entity.SecurityUser;

@Service
public class CustomAuthenticationProvider implements AuthenticationProvider
{
    private static final Logger logger = LoggerFactory.getLogger(CustomAuthenticationProvider.class);

    @Autowired
    AuthorityService userService;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    
    // @Autowired
    // private SaltSource saltSource;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException
    {
        String username = authentication.getName();
        String password = (String) authentication.getCredentials();

        SecurityUser user;
        // Collection<? extends GrantedAuthority> authorities;
        Collection<GrantedAuthority> authorities;

        try
        {
            user = (SecurityUser) userService.loadUserByUsername(username);

            String hashedPassword = passwordEncoder.encode(password);

            System.out.println("authenticate");
            System.out.println("authenticate");
            System.out.println("authenticate");
            System.out.println("authenticate");
            logger.info("username : " + username + " / password : " + password + " / hash password : " + hashedPassword);
            logger.info("username : " + user.getUsername() + " / password : " + user.getPassword());

            if (!hashedPassword.equals(user.getPassword()))
            {
                throw new BadCredentialsException("비밀번호가 일치하지 않습니다.");
            }

            authorities = user.getAuthorities();
            authorities.add(new SimpleGrantedAuthority("ADMIN"));
        }
        catch (UsernameNotFoundException e)
        {
            logger.info(e.toString());
            throw new UsernameNotFoundException(e.getMessage());
        }
        catch (BadCredentialsException e)
        {
            logger.info(e.toString());
            throw new BadCredentialsException(e.getMessage());
        }
        catch (Exception e)
        {
            logger.info(e.toString());
            throw new RuntimeException(e.getMessage());
        }

        return new UsernamePasswordAuthenticationToken(user, password, authorities);
    }

    @Override
    public boolean supports(Class<?> arg0)
    {
        return true;
    }
}