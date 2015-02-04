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
package com.geeksaga.forest.dao;

import java.util.List;

import com.geeksaga.forest.repositories.entity.Seed;

public interface SeedDao extends EntityDao<Seed>
{
    /**
     * <pre>
     * Seed 를 저장 한다.
     * 
     * 주의!. JPA 의 repository.save(seed) 는 저장하려는 seed 값이 아니라 저장 하려고 한 키를 값으로 데이터가 있는지 조회 한 값을 리턴 한다. 
     * </pre>
     * @param seed
     * @return
     */
    Seed save(Seed seed);
    
    List<Seed> save(Iterable<Seed> list);
    
    Seed findBySid(Long sid);
    
    List<Seed> findTopN(int page, int size);
}