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
import static org.junit.Assert.assertThat;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.geeksaga.common.crypt.PasswordEncoderWrapper;
import com.geeksaga.common.util.HangleParser;
import com.geeksaga.common.util.KeyGenerator;
import com.geeksaga.forest.entity.QSeed;
import com.geeksaga.forest.entity.Seed;
import com.geeksaga.forest.entity.Tag;
import com.geeksaga.forest.entity.TagMap;
import com.geeksaga.forest.entity.User;
import com.geeksaga.forest.util.AbstractRepositoryTestSupport;
import com.google.common.collect.Lists;
import com.mysema.query.types.Predicate;

public class SeedRepositoryTest extends AbstractRepositoryTestSupport
{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private static TagMapRepository tagMapRepository;

    @Autowired
    private SeedRepository seedRepository;

    private User user;

    @Before
    public void setup()
    {
        user = new User(KeyGenerator.generateKeyToLong(), "geeksaga@geeksaga.com", PasswordEncoderWrapper.encode("password"), "jihun", "0");

        userRepository.saveAndFlush(user);

        seedRepository.deleteAll();

        List<Seed> seeds = Lists.newArrayList();

        Seed seed1 = new Seed(KeyGenerator.generateKeyToLong(), "Test 1", "Content 1", user.getSid());
        Seed seed2 = new Seed(KeyGenerator.generateKeyToLong(), "Test 2", "Content 2", user.getSid());
        Seed seed3 = new Seed(KeyGenerator.generateKeyToLong(), "Test 3", "Content 3", user.getSid());

        seeds.add(seed1);
        seeds.add(seed2);
        seeds.add(seed3);

        Tag tag = new Tag(KeyGenerator.generateKeyToLong(), user.getSid(), "숲지도", HangleParser.parse("숲지도"));

        tagRepository.save(tag);

        Set<TagMap> tagMap = new HashSet<TagMap>();
        tagMap.add(new TagMap(seed1, tag));

        seed1.setTagSet(tagMap);

        seedRepository.save(seeds);
        seedRepository.flush();

        // tagRepository.save(tag);
    }

    @Test
    public void testSave()
    {
        Seed seed = new Seed(KeyGenerator.generateKeyToLong(), "Test Save 1", "Save Content 1", user.getSid());

        Seed savedSeed = seedRepository.saveAndFlush(seed);

        assertThat(savedSeed, is(notNullValue()));
        assertThat(seed.getSid(), is(savedSeed.getSid()));
    }

    @Test
    public void testFindByPage()
    {
        Pageable page = new PageRequest(0, 2);

        Page<Seed> findPage = seedRepository.findAll(page);

        if (findPage != null)
        {
            assertThat(2, is(findPage.getSize()));
        }
    }

    @Test
    public void testFindAll()
    {
        List<Seed> seeds = seedRepository.findAll();

        assertThat(3, is(seeds.size()));
    }

    @Test
    public void testFindAllByPredicate()
    {
        String title = "Test";

        QSeed qSeed = QSeed.seed;
        Predicate predicate = qSeed.title.like(title + "%");// .and(qSeed.status.eq(BookStatus.CanRent));

        Iterable<Seed> list = seedRepository.findAll(predicate);

        for (Seed seed : list)
        {
            assertThat(seed.getTitle().contains(title), is(true));
        }
    }

    @Test
    public void testFindTop10ByOrderByModifyTimestampDesc()
    {
        List<Seed> seeds = seedRepository.findTop10ByOrderByModifyTimestampDesc();

        assertThat(3, is(seeds.size()));

        System.out.println(seeds.get(0).getTagSet());
    }

    @Test
    public void testFindByUserSid()
    {
        List<Seed> seeds = seedRepository.findByUserSid(user.getSid());

        assertThat(3, is(seeds.size()));

        System.out.println(seeds.get(0).getTagSet());
        // System.out.println(seeds.get(0).getUser());
        // assertEquals(3, seeds.size());
    }
}