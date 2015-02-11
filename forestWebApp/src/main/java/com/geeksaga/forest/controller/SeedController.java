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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;

import com.geeksaga.forest.common.util.FileUtil;
import com.geeksaga.forest.common.util.RequestUtils;
import com.geeksaga.forest.entity.SecurityUser;
import com.geeksaga.forest.entity.Seed;
import com.geeksaga.forest.service.AuthorityService;
import com.geeksaga.forest.service.SeedCommandService;
import com.geeksaga.forest.service.SeedQueryService;

@Controller
public class SeedController
{
    @Autowired
    private SeedQueryService seedQueryServcie;
    
    @Autowired
    private SeedCommandService seedCommandServcie;

    @RequestMapping(value = { "/seed/add" }, method = RequestMethod.GET)
    public String save(Seed seed, Model model)
    {
        return "seed/add";
    }

    @RequestMapping(value = { "/seed/add" }, method = RequestMethod.POST)
    public String add(@Valid Seed seed, BindingResult bindingResult, WebRequest request) throws IOException
    {
        SecurityUser user = AuthorityService.getUser(request);

        if (user != null && !StringUtils.isEmpty(user.getSid()))
        {
            seed.setUserSid(user.getSid());
        }
        else
        {
            seed.setUserSid(0L);
        }

        System.out.println(seed);

        // if (!file.isEmpty())
        // {
        // byte[] bytes = file.getBytes();

        seed.setFiles(FileUtil.processFile(RequestUtils.getRequest(request), seed.getFile(), "0"));
        // }
        // if (bindingResult.hasErrors()) {
        //
        // }

        seedCommandServcie.save(seed);

        return "redirect:/index";
    }

    // @RequestMapping(value = "/form", method = RequestMethod.POST)
    // public String handleFormUpload(Seed seed, @RequestParam("file") MultipartFile file) throws IOException
    // {
    // if (!file.isEmpty())
    // {
    // byte[] bytes = file.getBytes();
    // // store the bytes somewhere
    // return "redirect:uploadSuccess";
    // }
    //
    // return "redirect:uploadFailure";
    // }

    // @RequestMapping(value = "/form", method = RequestMethod.POST)
    // public String handleFormUpload(@RequestParam("name") String name, @RequestParam("file") Part file) throws IOException
    // {
    // InputStream inputStream = file.getInputStream();
    // // store bytes from uploaded file somewhere
    //
    // return "redirect:uploadSuccess";
    // }

    // @ExceptionHandler(IOException.class)
    // public ResponseEntity<String> handleIOException(IOException ex) {
    // // prepare responseEntity
    // return responseEntity;
    // }

}