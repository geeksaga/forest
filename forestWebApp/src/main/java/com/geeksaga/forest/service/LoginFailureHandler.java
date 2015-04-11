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

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

@Component
public class LoginFailureHandler implements AuthenticationFailureHandler
{
    private static final Logger logger = LoggerFactory.getLogger(LoginFailureHandler.class);

    public LoginFailureHandler()
    {}

    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException authenticationException) throws IOException, ServletException
    {
        request.setAttribute("username", request.getParameter("username"));
        request.setAttribute("password", request.getParameter("password"));

        request.getSession().setAttribute("loginMessage", authenticationException.getMessage());

        if (authenticationException instanceof AuthenticationServiceException)
        {}

        if (authenticationException instanceof BadCredentialsException)
        {}

        if (authenticationException instanceof CredentialsExpiredException)
        {}

        if (authenticationException instanceof LockedException)
        {}

        if (authenticationException instanceof DisabledException)
        {}

        response.sendRedirect(request.getContextPath() + "/login");
    }
}