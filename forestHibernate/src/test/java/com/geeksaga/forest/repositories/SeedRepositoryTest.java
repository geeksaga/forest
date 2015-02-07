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
package com.geeksaga.forest.repositories;

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
import com.geeksaga.forest.entity.QSeed;
import com.geeksaga.forest.entity.Seed;
import com.geeksaga.forest.util.AbstractRepositoryTestSupport;
import com.google.common.collect.Lists;
import com.mysema.query.types.Predicate;

public class SeedRepositoryTest extends AbstractRepositoryTestSupport
{
    @Autowired
    private SeedRepository seedRepository;

    @Before
    public void setup()
    {
        seedRepository.deleteAll();

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

        seedRepository.save(seeds);
        seedRepository.flush();
    }

    @Test
    public void save()
    {
        Seed seed = new Seed(KeyGenerator.generateKeyToLong(), "Test Save 1", "Save Content 1", 0l, DateConvertor.getDateTimeFormat(),
                DateConvertor.getDateTimeFormat());

        Seed savedSeed = seedRepository.saveAndFlush(seed);

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

        assertEquals(3, seeds.size());
    }
    
    @Test
    public void findAllByPredicate()
    {
        String title = "Test";
        
        QSeed qSeed = QSeed.seed;
        Predicate predicate = qSeed.title.like(title + "%");// .and(qSeed.status.eq(BookStatus.CanRent));
        
        Iterable<Seed> books = seedRepository.findAll(predicate);
        
        for (Seed book : books)
        {
            assertThat(book.getTitle().contains(title), is(true));
        }
    }
}