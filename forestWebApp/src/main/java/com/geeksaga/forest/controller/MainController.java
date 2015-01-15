package com.geeksaga.forest.controller;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController
{
    // @RequestMapping(value = { "/" }, method = RequestMethod.GET)
    // public String main(Model model)
    // {
    // System.out.println("call??");
    //
    // return "index";
    // }
    
    @RequestMapping("/a")
    @ResponseBody
    String hello() {
        return "Hello World";
    }
    
    @Value("${application.message:Hello World}")
    private String message = "Hello World";

    @RequestMapping("/")
    public String welcome(Map<String, Object> model)
    {
        model.put("time", new Date());
        model.put("message", this.message);

        System.out.println("welcome");

        return "welcome";
    }

    @RequestMapping(value = { "/index" }, method = RequestMethod.GET)
    public ModelAndView index(ModelMap model)
    {
        return new ModelAndView("index");
    }

    @RequestMapping("/foo")
    public String foo(Map<String, Object> model)
    {
        throw new RuntimeException("Foo");
    }
}