package com.geeksaga.forest.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

@Component
public class LoginFailureHandler implements AuthenticationFailureHandler
{
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException auth)
            throws IOException, ServletException
    {
        response.sendRedirect(request.getContextPath() + "/login");
    }
}