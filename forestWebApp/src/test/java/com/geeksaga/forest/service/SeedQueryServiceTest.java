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
package com.geeksaga.forest.service;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import com.geeksaga.common.crypt.PasswordEncoderWrapper;
import com.geeksaga.common.util.KeyGenerator;
import com.geeksaga.forest.AbstractTestSupport;
import com.geeksaga.forest.entity.Seed;
import com.geeksaga.forest.entity.User;
import com.google.common.collect.Lists;

public class SeedQueryServiceTest extends AbstractTestSupport
{
    @Autowired
    private UserCommandService userCOmmandServce;

    @Autowired
    private SeedQueryService seedQueryService;

    @Autowired
    private SeedCommandService seedCommandService;

    private User user;

    @Before
    public void setUp()
    {
        initTestData();
    }

    private void initTestData()
    {
        user = new User(KeyGenerator.generateKeyToLong(), "geeksaga@geeksaga.com", PasswordEncoderWrapper.encode("password"), "jihun", "0");

        userCOmmandServce.save(user);

        List<Seed> seeds = Lists.newArrayList();

        Seed seed1 = new Seed(KeyGenerator.generateKeyToLong(), "Test 1", "Content 1", user.getSid());

        Seed seed2 = new Seed(KeyGenerator.generateKeyToLong(), "Test 2", "Content 2", user.getSid());

        Seed seed3 = new Seed(KeyGenerator.generateKeyToLong(), "Test 3", "Content 3", user.getSid());

        seeds.add(seed1);
        seeds.add(seed2);
        seeds.add(seed3);

        seedCommandService.save(seeds);
    }

    @Test
    public void testFindTopN()
    {
        Page<Seed> page = seedQueryService.findTopN(0, 2);

        assertThat(2, is(page.getContent().size()));
    }
    
    @Test
    public void testFindTop10ByOrderByModifyTimestampDesc()
    {
        List<Seed> list = seedQueryService.findTop10ByOrderByModifyTimestampDesc();

        assertThat(3, is(list.size()));
    }
}