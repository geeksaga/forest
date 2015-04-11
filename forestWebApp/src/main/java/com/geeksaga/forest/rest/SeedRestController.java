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
package com.geeksaga.forest.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.geeksaga.common.util.DateConvertor;
import com.geeksaga.forest.common.util.Logger;
import com.geeksaga.forest.entity.Seed;
import com.geeksaga.forest.service.SeedCommandService;
import com.geeksaga.forest.service.SeedQueryService;

@RestController
public class SeedRestController
{
    @Autowired
    private SeedQueryService seedQueryServcie;
    
    @Autowired
    private SeedCommandService seedCommandServcie;

    @RequestMapping("/seeds/list")
    public List<Seed> find(@RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size)
    {
        return seedQueryServcie.findTopN(page, size).getContent();
    }

    @RequestMapping("/seeds/save")
    public Seed save(Seed seed)
    {
        Logger.debug(seed.toString());

        seed.setTitle("Test 1 " + DateConvertor.getDateTimeFormat());
        seed.setContent("Test Content 1");

        return seedCommandServcie.save(seed);
    }
}