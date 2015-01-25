package com.geeksaga.forest.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.geeksaga.forest.dao.SeedDao;
import com.geeksaga.forest.repositories.entity.Seed;
import com.geeksaga.forest.service.SeedService;

/**
 * @author geeksaga
 * @version 0.1
 */
@Service
@Transactional
public class SeedServiceImpl implements SeedService
{
    @Autowired
    private SeedDao seedDao;

    public SeedServiceImpl()
    {}
    
    @Transactional
    public Seed save(Seed seed)
    {
        return seedDao.save(seed);
    }

    @Transactional(readOnly = true)
    public List<Seed> findTopN(int page, int size)
    {
        return seedDao.findTopN(page, size);
    }
}
