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

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.config.BeanIds;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;

import com.geeksaga.forest.entity.SecurityUser;

@Service(BeanIds.USER_DETAILS_SERVICE)
public class CustomUserDetailService implements UserDetailsService
{
    private static final Logger logger = LoggerFactory.getLogger(CustomUserDetailService.class);

    @Autowired
    protected UserCommandService userCommandService;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException
    {
        // StringTokenizer st = new StringTokenizer(email, "___");
        // user.setPid(st.nextToken());
        
        SecurityUser securityUser = new SecurityUser(username);

        SecurityUser authenticateUser = null;

        try
        {
            authenticateUser = userCommandService.authenticate(securityUser);
        }
        catch (Exception e)
        {
            logger.info(e.getMessage(), e);
        }

        return (authenticateUser == null) ? securityUser : authenticateUser;
    }

    public static SecurityUser getUser(Object object)
    {
        SecurityContextImpl securityContextImpl = null;

        if (object instanceof HttpSession)
        {
            securityContextImpl = (SecurityContextImpl) ((HttpSession) object).getAttribute("SPRING_SECURITY_CONTEXT");

            Authentication authentication = securityContextImpl.getAuthentication();

            return (SecurityUser) authentication.getPrincipal();
        }

        return new SecurityUser();
    }

    public static SecurityUser getUser(WebRequest request)
    {
        SecurityContextImpl securityContextImpl = (SecurityContextImpl) request.getAttribute("SPRING_SECURITY_CONTEXT",
                WebRequest.SCOPE_SESSION);

        if (securityContextImpl == null)
        {
            return new SecurityUser();
        }

        Authentication authentication = securityContextImpl.getAuthentication();

        return (SecurityUser) authentication.getPrincipal();
    }

    public static SecurityUser getUser(HttpSession session)
    {
        SecurityContextImpl securityContextImpl = (SecurityContextImpl) session.getAttribute("SPRING_SECURITY_CONTEXT");

        if (securityContextImpl == null)
        {
            return new SecurityUser();
        }

        Authentication authentication = securityContextImpl.getAuthentication();

        return (SecurityUser) authentication.getPrincipal();
    }
}