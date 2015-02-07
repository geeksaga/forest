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

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.geeksaga.forest.dao.SeedDao;
import com.geeksaga.forest.entity.Seed;
import com.geeksaga.forest.entity.Tag;
import com.geeksaga.forest.entity.TagMap.CNT_TYPE;
import com.geeksaga.forest.search.LuceneEngine;
import com.geeksaga.forest.service.SeedService;
import com.geeksaga.forest.service.TagMapService;

@Service
@Transactional
public class SeedServiceImpl implements SeedService
{
    @Autowired
    private SeedDao seedDao;
    @Autowired
    private TagMapService tagMapService;

    private LuceneEngine luceneEngine = LuceneEngine.getInstance();

    public SeedServiceImpl()
    {}

    /*
     * (non-Javadoc)
     * 
     * @see com.geeksaga.forest.service.SeedService#add(com.geeksaga.forest.repositories.entity.Seed)
     */
    @Transactional
    public Seed save(Seed seed)
    {
        try
        {
            luceneEngine.addDocument(seed.getIndexingData(), seed.getSid());

            seed = seedDao.save(seed);
            
            if (!StringUtils.isEmpty(seed.getTag()))
            {
                Tag tags = new Tag();
                tags.setTargetSid(seed.getSid());
                tags.setTagName(seed.getTag());

                tagMapService.add(seed, tags, CNT_TYPE.TAG_PLUS_CNT);
            }
        }
        catch (Throwable thr)
        {
            luceneEngine.deleteDocument(seed.getSid());

            thr.printStackTrace();
        }

        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.geeksaga.forest.service.SeedService#findTopN(int, int)
     */
    @Transactional(readOnly = true)
    public List<Seed> findTopN(int page, int size)
    {
        return seedDao.findTopN(page, size);
    }
}