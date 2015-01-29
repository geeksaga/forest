package com.geeksaga.forest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.geeksaga.forest.repositories.entity.Seed;
import com.geeksaga.forest.service.SeedService;

/**
 * @author geeksaga
 * @version 0.1
 */
@RestController
public class SeedController
{
    @Autowired
    private SeedService seedServcie;

    @RequestMapping(value = { "/seed/add" }, method = RequestMethod.GET)
    public ModelAndView save(Seed seed, ModelMap model)
    {
        return new ModelAndView("seed/add");
    }
}