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
package com.geeksaga.forest.dao.impl;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.geeksaga.common.util.KeyGenerator;
import com.geeksaga.forest.dao.TagDao;
import com.geeksaga.forest.entity.Tag;
import com.geeksaga.forest.repositories.TagRepository;

@Repository
public class TagDaoImpl extends AbstractSpringData<Tag> implements TagDao
{
    @Autowired
    private TagRepository tagRepository;

    public TagDaoImpl()
    {
        super(Tag.class);
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see com.geeksaga.forest.dao.TagDao#save(com.geeksaga.forest.repositories.entity.Tag)
     */
    public Tag save(Tag tag)
    {
        tag.setSid(KeyGenerator.generateKeyToLong());

        tagRepository.save(tag);

        return tag;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.geeksaga.forest.dao.TagDao#save(java.lang.Iterable)
     */
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

    /*
     * (non-Javadoc)
     * 
     * @see com.geeksaga.forest.dao.TagDao#findByTagName(com.geeksaga.forest.repositories.entity.Tag)
     */
    public Tag findByTagName(Tag tag)
    {
        return tagRepository.findByTagName(tag.getTagName());
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.geeksaga.forest.dao.TagDao#updateCnt(com.geeksaga.forest.repositories.entity.Tag)
     */
    public int updateCnt(Tag tag)
    {
        return tagRepository.updateCnt(tag.getTagName());
    }

    public List<Tag> findAll()
    {
        return tagRepository.findAll();
    }
}