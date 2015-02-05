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
package com.geeksaga.forest.repositories.entity;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.geeksaga.common.util.DateConvertor;
import com.geeksaga.common.util.KeyGenerator;
import com.geeksaga.forest.repositories.SeedRepository;
import com.geeksaga.forest.util.AbstractRepositoryTestSupport;
import com.google.common.collect.Lists;

public class SeedRepositoryTest extends AbstractRepositoryTestSupport
{
    @Autowired
    private SeedRepository seedRepository;

    @Before
    public void setup()
    {
        seedRepository.deleteAll();

        List<Seed> seeds = Lists.newArrayList();

        Seed seed1 = new Seed();
        seed1.setSid(KeyGenerator.generateKeyToLong());
        seed1.setTitle("Test 1");
        seed1.setContent("Test Content 1");
        seed1.setDelYn("N");
        seed1.setRegistTimestamp(DateConvertor.getDateTimeFormat());
        seed1.setModifyTimestamp(DateConvertor.getDateTimeFormat());

        Seed seed2 = new Seed();
        seed2.setSid(KeyGenerator.generateKeyToLong());
        seed2.setTitle("Test 2");
        seed2.setContent("Test Content 2");
        seed2.setDelYn("N");
        seed2.setRegistTimestamp(DateConvertor.getDateTimeFormat());
        seed2.setModifyTimestamp(DateConvertor.getDateTimeFormat());

        Seed seed3 = new Seed();
        seed3.setSid(KeyGenerator.generateKeyToLong());
        seed3.setTitle("Test 3");
        seed3.setContent("Test Content 3");
        seed3.setDelYn("N");
        seed3.setRegistTimestamp(DateConvertor.getDateTimeFormat());
        seed3.setModifyTimestamp(DateConvertor.getDateTimeFormat());

        seeds.add(seed1);
        seeds.add(seed2);
        seeds.add(seed3);

        seedRepository.save(seeds);
    }

    @Test
    public void save()
    {
        Seed seed = new Seed();
        seed.setSid(KeyGenerator.generateKeyToLong());
        seed.setTitle("Test Save 1");
        seed.setContent("Test Save Content 1");
        seed.setDelYn("N");

        Seed savedSeed = seedRepository.save(seed);

        assertThat(savedSeed, is(notNullValue()));
        assertThat(seed.getSid(), is(savedSeed.getSid()));
    }

    @Test
    public void findByPage()
    {
        Pageable page = new PageRequest(0, 2);

        Page<Seed> findPage = seedRepository.findAll(page);

        if (findPage != null)
        {
            assertThat(2, is(findPage.getSize()));
        }
    }

    @Test
    public void findAll()
    {
        List<Seed> seeds = (List<Seed>) seedRepository.findAll();

        System.out.println(seeds);
        assertEquals(3, seeds.size());
    }
}