package com.geeksaga.forest.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.geeksaga.common.util.DateConvertor;
import com.geeksaga.forest.repositories.entity.Seed;
import com.geeksaga.forest.service.SeedService;

/**
 * @author geeksaga
 * @version 0.1
 */
@RestController
public class SeedRestController
{
    @Autowired
    private SeedService seedServcie;

    @RequestMapping("/seeds/list")
    public List<Seed> find(@RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size)
    {
        return seedServcie.findTopN(page, size);
    }

    @RequestMapping("/seeds/save")
    public Seed save(Seed seed)
    {
        System.out.println(seed);

        seed.setTitle("Test 1 " + DateConvertor.getDateTimeFormat());
        seed.setContent("Test Content 1");

        return seedServcie.save(seed);
    }
}