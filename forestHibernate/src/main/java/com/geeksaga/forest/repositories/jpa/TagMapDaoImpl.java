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
package com.geeksaga.forest.repositories.jpa;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.geeksaga.common.util.DateConvertor;
import com.geeksaga.forest.dao.TagMapDao;
import com.geeksaga.forest.repositories.TagMapRepository;
import com.geeksaga.forest.repositories.entity.TagMap;

@Repository
public class TagMapDaoImpl implements TagMapDao
{
    @Autowired
    private TagMapRepository tagMapRepository;

    /*
     * (non-Javadoc)
     * 
     * @see com.geeksaga.forest.dao.TagMapDao#save(com.geeksaga.forest.repositories.entity.TagMap)
     */
    public TagMap save(TagMap tagMap)
    {
        tagMap.setRegistTimestamp(DateConvertor.getDateTimeFormat());

        tagMapRepository.save(tagMap);

        return tagMap;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.geeksaga.forest.dao.TagMapDao#save(java.lang.Iterable)
     */
    public List<TagMap> save(Iterable<TagMap> list)
    {
        Iterator<TagMap> iter = list.iterator();

        while (iter.hasNext())
        {
            TagMap tagMap = iter.next();
            tagMap.setRegistTimestamp(DateConvertor.getDateTimeFormat());
        }

        tagMapRepository.save(list);
        
        return (List<TagMap>) list;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.geeksaga.forest.dao.TagMapDao#exists(com.geeksaga.forest.repositories.entity.TagMap)
     */
    public boolean exists(TagMap tagMap)
    {
        return tagMapRepository.exists(tagMap);
    }

    // public Seed findBySid(Long sid)
    // {
    // return tagMapRepository.findOne(QSeed.seed.sid.eq(sid));
    // }
}
