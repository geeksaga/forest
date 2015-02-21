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
package com.geeksaga.forest.controller;

import java.io.IOException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;

import com.geeksaga.common.util.KeyGenerator;
import com.geeksaga.forest.entity.Authentication;
import com.geeksaga.forest.entity.User;
import com.geeksaga.forest.message.AuthenticationMailMessage;
import com.geeksaga.forest.message.SignupMessage;
import com.geeksaga.forest.service.AuthenticationService;
import com.geeksaga.forest.service.JabberService;
import com.geeksaga.forest.service.SendMailService;
import com.geeksaga.forest.service.UserCommandService;
import com.geeksaga.forest.service.UserQueryService;

@Controller
public class UserController
{
    @Autowired
    private UserQueryService userQueryServcie;

    @Autowired
    private UserCommandService userCommandServcie;

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private SendMailService sendMailService;

    @Autowired
    private TaskExecutor taskExecutor;

    @Autowired
    private JabberService jabberService;

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

        Authentication authentication = new Authentication();
        authentication.setSid(KeyGenerator.generateKeyToLong());
        authentication.setUserSid(user.getSid());
        authentication.setAuthenticationKey(KeyGenerator.generateKeyToLong());

        authenticationService.save(authentication);

        SignupMessage message = new AuthenticationMailMessage(user, authentication.getAuthenticationKey());
        sendMailService.setMessage(message);

        jabberService.sendMessage("smileemaker@gmail.com", user.toString());

        // taskExecutor.execute(sendMailService);

        return "redirect:/index";
    }
}