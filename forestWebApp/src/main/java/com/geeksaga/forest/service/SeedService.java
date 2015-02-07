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

import com.geeksaga.forest.entity.Seed;

public interface SeedService
{
    /**
     * Seed 를 추가(등록) 한다.
     * 
     * @param seed
     * @return
     */
    Seed save(Seed seed);

    /**
     * 최근 추가된 Seed N 개를  조회 한다.
     * 
     * @param page
     * @param size
     * @return
     */
    List<Seed> findTopN(int page, int size);
}