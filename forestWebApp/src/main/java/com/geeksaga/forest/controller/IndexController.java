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

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.geeksaga.forest.service.SeedQueryService;

@Controller
public class IndexController
{
    @Autowired
    private SeedQueryService seedQueryServcie;
    
    @Value("${application.message:Hello Forest}")
    private String message = "Hello Forest";

    @RequestMapping("/welcome")
    public String welcome(Map<String, Object> model)
    {
        model.put("time", new Date());
        model.put("message", this.message);

        return "welcome";
    }

    @RequestMapping(value = { "/", "/index" }, method = RequestMethod.GET)
    public String index(ModelMap model)
    {
        model.addAttribute("seeds", seedQueryServcie.findTop10ByOrderByModifyTimestampDesc());
        
        model.put("message", this.message);
        
        return "index";
    }
}