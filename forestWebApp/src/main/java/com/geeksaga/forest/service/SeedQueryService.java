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

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.geeksaga.forest.entity.QSeed;
import com.geeksaga.forest.entity.Seed;
import com.geeksaga.forest.repositories.SeedRepository;

@Service
public class SeedQueryService
{
    @Autowired
    private SeedRepository seedRepository;
    
    @Transactional(readOnly = true)
    public List<Seed> findAll()
    {
        return seedRepository.findAll();
    }
    
    /**
     * 최근 추가된 Seed N 개를  조회 한다.
     * 
     * @param page
     * @param size
     * @return
     */
    @Transactional(readOnly = true)
    public Page<Seed> findTopN(int page, int size)
    {
        Pageable pageable = new PageRequest(page, size, Direction.DESC, "modifyTimestamp");

        return seedRepository.findAll(pageable);
    }
    
    @Transactional(readOnly = true)
    public List<Seed> findTop10ByOrderByModifyTimestampDesc()
    {
        return seedRepository.findTop10ByOrderByModifyTimestampDesc();
    }
    
    @Transactional(readOnly = true)
    public Seed findBySid(Long sid)
    {
        return seedRepository.findOne(QSeed.seed.sid.eq(sid));
    }
}