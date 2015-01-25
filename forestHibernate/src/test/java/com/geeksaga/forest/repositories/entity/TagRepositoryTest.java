package com.geeksaga.forest.repositories.entity;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.geeksaga.common.util.HangleParser;
import com.geeksaga.common.util.KeyGenerator;
import com.geeksaga.forest.repositories.TagRepository;
import com.geeksaga.forest.util.AbstractRepositoryTestSupport;
import com.google.common.collect.Lists;

/**
 * @author geeksaga
 * @version 0.1
 */
public class TagRepositoryTest extends AbstractRepositoryTestSupport
{
    @Autowired
    private TagRepository repository;

    @Before
    public void setup()
    {
        repository.deleteAll();

        List<Tag> tags = Lists.newArrayList();

        Tag tag1 = new Tag("숲", HangleParser.parse("숲"));
        tag1.setSid(KeyGenerator.generateKeyToLong());
        tag1.setTargetSid(KeyGenerator.generateKeyToLong());

        Tag tag2 = new Tag("현진", HangleParser.parse("현진"));
        tag2.setSid(KeyGenerator.generateKeyToLong());
        tag2.setTargetSid(KeyGenerator.generateKeyToLong());

        Tag tag3 = new Tag("자바", HangleParser.parse("자바"));
        tag3.setSid(KeyGenerator.generateKeyToLong());
        tag3.setTargetSid(KeyGenerator.generateKeyToLong());

        tags.add(tag1);
        tags.add(tag2);
        tags.add(tag3);

        repository.save(tags);
    }

    @Test
    public void save()
    {
        Tag tag = new Tag("저장", HangleParser.parse("저장"));
        tag.setSid(KeyGenerator.generateKeyToLong());
        tag.setTargetSid(KeyGenerator.generateKeyToLong());

        Tag savedTag = repository.saveAndFlush(tag);

        assertThat(savedTag, is(notNullValue()));
        assertThat(tag.getSid(), is(savedTag.getSid()));
        
        assertThat(1, is(repository.updateCnt(tag.getTagName())));
        assertThat(1, is(repository.updateCnt(tag.getTagName())));

        Tag findedTag = repository.findByTagName(tag.getTagName());
        
        assertThat(2, is(findedTag.getHitCnt()));
    }

    @Test
    public void findByTagName()
    {
        Tag tag = new Tag("자바", HangleParser.parse("자바"));

        Tag findedTag = repository.findByTagName(tag.getTagName());

        assertThat(findedTag, is(notNullValue()));
    }
}