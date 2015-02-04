package com.geeksaga.forest.controller;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController
{
    @Value("${application.message:Hello World}")
    private String message = "Hello World";

    @RequestMapping("/welcome")
    public String welcome(Map<String, Object> model)
    {
        model.put("time", new Date());
        model.put("message", this.message);

        System.out.println("welcome");

        return "welcome";
    }

    @RequestMapping(value = { "/", "/index" }, method = RequestMethod.GET)
    public String index()
    {
        return "index";
    }
}