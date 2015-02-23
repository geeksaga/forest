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
package com.geeksaga.forest.controller.dev;

import java.io.IOException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;

import com.geeksaga.forest.Profiles;
import com.geeksaga.forest.entity.Authentication;
import com.geeksaga.forest.entity.User;
import com.geeksaga.forest.service.AuthenticationService;
import com.geeksaga.forest.service.UserCommandService;

@Profile(Profiles.DEV)
@Controller
public class UserDevController
{
    @Autowired
    private UserCommandService userCommandServcie;

    @Autowired
    private AuthenticationService authenticationService;

    @RequestMapping(value = { "/signup" }, method = RequestMethod.GET)
    public String signup(ModelMap model)
    {
        model.addAttribute("user", new User());

        return "user/signup";
    }
    
    @RequestMapping(value = { "/signup" }, method = RequestMethod.POST)
    public String add(@Valid User user, BindingResult bindingResult, WebRequest request) throws IOException
    {
        userCommandServcie.save(user);

        authenticationService.save(new Authentication(user.getSid()));

        return "redirect:/";
    }
}