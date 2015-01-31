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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.geeksaga.forest.dao.SeedDao;
import com.geeksaga.forest.repositories.entity.Seed;
import com.geeksaga.forest.search.LuceneEngine;
import com.geeksaga.forest.service.SeedService;

@Service
@Transactional
public class SeedServiceImpl implements SeedService
{
    @Autowired
    private SeedDao seedDao;

    private LuceneEngine luceneEngine = LuceneEngine.getInstance();

    public SeedServiceImpl()
    {}

    @Transactional
    public Seed save(Seed seed)
    {
        try
        {
            luceneEngine.addDocument(seed.getIndexingData(), seed.getSid());
            
            return seedDao.save(seed);
        }
        catch (Throwable thr)
        {
            luceneEngine.deleteDocument(seed.getSid());
        }
        
        return null;
    }

    @Transactional(readOnly = true)
    public List<Seed> findTopN(int page, int size)
    {
        return seedDao.findTopN(page, size);
    }
}