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

import java.util.List;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.geeksaga.common.crypt.PasswordEncoderWrapper;
import com.geeksaga.common.util.DateConvertor;
import com.geeksaga.common.util.HangleParser;
import com.geeksaga.common.util.KeyGenerator;
import com.geeksaga.forest.entity.Seed;
import com.geeksaga.forest.entity.Tag;
import com.geeksaga.forest.entity.TagMap;
import com.geeksaga.forest.entity.User;
import com.geeksaga.forest.util.AbstractRepositoryTestSupport;

public class TagMapRepositoryTest extends AbstractRepositoryTestSupport
{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SeedRepository seedRepository;

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private static TagMapRepository tagMapRepository;

    @Autowired(required = true)
    public void setTagMapRepository(TagMapRepository tagMapRepository)
    {
        TagMapRepositoryTest.tagMapRepository = tagMapRepository;
    }

    private User user;

    @Before
    public void setup()
    {
        userRepository.deleteAll();
        seedRepository.deleteAll();
        tagRepository.deleteAll();

        user = new User(KeyGenerator.generateKeyToLong(), "geeksaga@geeksaga.com", PasswordEncoderWrapper.encode("password"), "jihun", "0");

        userRepository.save(user);

        Seed seed = new Seed(KeyGenerator.generateKeyToLong(), "태그 맵을 위한 데이터", "태그 맵을 위한 데이터 내용", user, DateConvertor.getDateTimeFormat(),
                DateConvertor.getDateTimeFormat());

        seedRepository.saveAndFlush(seed);

        Tag tag = new Tag("숲지도", HangleParser.parse("숲지도"));
        tag.setSid(KeyGenerator.generateKeyToLong());
        tag.setTargetSid(KeyGenerator.generateKeyToLong());

        tagRepository.saveAndFlush(tag);

        TagMap tagMap = new TagMap(seed, tag);
        tagMap.setRegistTimestamp(DateConvertor.getDateTimeFormat());

        tagMapRepository.saveAndFlush(tagMap);
    }

    @Test
    public void testSave()
    {
        Seed seed = new Seed(KeyGenerator.generateKeyToLong(), "태그 맵을 위한 테스트", "태그 맵을 위한 테스트 내용", user, DateConvertor.getDateTimeFormat(),
                DateConvertor.getDateTimeFormat());

        seedRepository.saveAndFlush(seed);

        Tag tag = new Tag("저장지도", HangleParser.parse("저장지도"));
        tag.setSid(KeyGenerator.generateKeyToLong());
        tag.setTargetSid(KeyGenerator.generateKeyToLong());

        tagRepository.saveAndFlush(tag);

        TagMap tagMap = new TagMap(seed, tag);
        tagMap.setRegistTimestamp(DateConvertor.getDateTimeFormat());

        TagMap savedTagMap = tagMapRepository.saveAndFlush(tagMap);

        assertThat(savedTagMap, is(notNullValue()));
        assertThat(tag, is(savedTagMap.getTag()));
    }

    @Test
    public void testFindAll()
    {
        List<TagMap> tags = tagMapRepository.findAll();

        assertThat(1, is(tags.size()));
    }

    @AfterClass
    public static void destory()
    {
        tagMapRepository.deleteAll();
    }
}