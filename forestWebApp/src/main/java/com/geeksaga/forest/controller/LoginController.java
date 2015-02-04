package com.geeksaga.forest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;

import com.geeksaga.forest.common.util.RequestUtils;
import com.geeksaga.forest.repositories.entity.SecurityUser;
import com.geeksaga.forest.service.UserAuthenticationService;
import com.geeksaga.forest.service.UserService;

/**
 * @author geeksaga
 * @version 0.1
 */
@Controller
public class LoginController
{
    @Autowired
    private UserService userService;
    @Autowired
    private UserAuthenticationService userAuthenticationService;

    @RequestMapping(value = { "/login_post" })
    public String login(@ModelAttribute SecurityUser securityUser, BindingResult result, WebRequest request)
    {
        System.out.println("Login POST");
        System.out.println(setSession(request, securityUser, false));

        return "welcome";
    }

    private boolean setSession(WebRequest request, SecurityUser securityUser, boolean loginHistory)
    {
        SecurityUser authenticateUser = userService.authenticate(securityUser);

        if (authenticateUser != null)
        {
            Authentication authentication = new UsernamePasswordAuthenticationToken(securityUser.getEmail(), securityUser.getPassword());
            SecurityContextHolder.getContext().setAuthentication(userAuthenticationService.authenticate(authentication, authenticateUser));

            RequestUtils.getSession(request).setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());

            // if(loginHistory) {
            // loginHistoryService.add(authenticateUser, ACTION_CODE.SUCCESS.getCode(), RequestUtils.getRemoteAddr(request));
            // }

            return true;
        }

        return false;
    }
}