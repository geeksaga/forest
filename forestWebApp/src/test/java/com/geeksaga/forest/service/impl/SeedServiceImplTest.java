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
package com.geeksaga.forest.service.impl;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.geeksaga.common.util.DateConvertor;
import com.geeksaga.common.util.KeyGenerator;
import com.geeksaga.forest.AbstractTestSupport;
import com.geeksaga.forest.dao.SeedDao;
import com.geeksaga.forest.entity.Seed;
import com.geeksaga.forest.service.SeedService;
import com.google.common.collect.Lists;

public class SeedServiceImplTest extends AbstractTestSupport
{
    @Autowired
    private SeedService seedService;
    
    @Autowired
    private SeedDao seedDao;

    @Before
    public void setUp()
    {
        initTestData();
    }

    private void initTestData()
    {
        List<Seed> seeds = Lists.newArrayList();

        Seed seed1 = new Seed(KeyGenerator.generateKeyToLong(), "Test 1", "Content 1", 0l, DateConvertor.getDateTimeFormat(),
                DateConvertor.getDateTimeFormat());

        Seed seed2 = new Seed(KeyGenerator.generateKeyToLong(), "Test 2", "Content 2", 0l, DateConvertor.getDateTimeFormat(),
                DateConvertor.getDateTimeFormat());

        Seed seed3 = new Seed(KeyGenerator.generateKeyToLong(), "Test 3", "Content 3", 0l, DateConvertor.getDateTimeFormat(),
                DateConvertor.getDateTimeFormat());
        
        seeds.add(seed1);
        seeds.add(seed2);
        seeds.add(seed3);
        
        seedDao.save(seeds);
    }
    
    @Test
    public void testSave()
    {
        Seed seed = new Seed(KeyGenerator.generateKeyToLong(), "Test Save 1", "Save Content 1", 0l, DateConvertor.getDateTimeFormat(),
                DateConvertor.getDateTimeFormat());
        
        seedService.save(seed);
    }
    
    @Test
    public void testFindTopN()
    {
        List<Seed> list = seedService.findTopN(0, 2);
        
        assertThat(2, is(list.size()));
    }
}