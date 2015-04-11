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

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.geeksaga.common.util.KeyGenerator;
import com.geeksaga.forest.entity.Tag;
import com.geeksaga.forest.repositories.TagRepository;

@Service
public class TagService extends AbstractSpringData<Tag>
{
    @Autowired
    private TagRepository tagRepository;

    public TagService()
    {
        super(Tag.class);
    }
    
    public Tag save(Tag tag)
    {
        tag.setSid(KeyGenerator.generateKeyToLong());

        tagRepository.save(tag);

        return tag;
    }

    public List<Tag> save(Iterable<Tag> list)
    {
        Iterator<Tag> iter = list.iterator();

        while (iter.hasNext())
        {
            Tag seed = iter.next();
            seed.setSid(KeyGenerator.generateKeyToLong());
        }

        tagRepository.save(list);

        return (List<Tag>) list;
    }

    public Tag findByTagName(Tag tag)
    {
        return tagRepository.findByTagName(tag.getTagName());
    }

    public int updateCnt(Tag tag)
    {
        return tagRepository.updateCnt(tag.getTagName());
    }

    public List<Tag> findAll()
    {
        return tagRepository.findAll();
    }
}