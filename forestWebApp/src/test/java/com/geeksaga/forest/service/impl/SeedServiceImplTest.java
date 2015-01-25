package com.geeksaga.forest.service.impl;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.geeksaga.forest.AbstractTestSupport;
import com.geeksaga.forest.dao.SeedDao;
import com.geeksaga.forest.repositories.entity.Seed;
import com.geeksaga.forest.service.SeedService;
import com.google.common.collect.Lists;

/**
 * @author geeksaga
 * @version 0.1
 */
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

        Seed seed1 = new Seed();
        seed1.setTitle("Test 1");
        seed1.setContent("Test Content 1");
        
        Seed seed2 = new Seed();
        seed2.setTitle("Test 2");
        seed2.setContent("Test Content 2");
        
        Seed seed3 = new Seed();
        seed3.setTitle("Test 3");
        seed3.setContent("Test Content 3");

        seeds.add(seed1);
        seeds.add(seed2);
        seeds.add(seed3);
        
        seedDao.save(seeds);
    }
    
    @Test
    public void testSave()
    {
        Seed seed = new Seed();
        seed.setTitle("Test 1");
        seed.setContent("Test Content 1");
        
        seedService.save(seed);
    }
    
    @Test
    public void testFindTopN()
    {
        List<Seed> list = seedService.findTopN(0, 2);
        
        assertThat(2, is(list.size()));
    }
}