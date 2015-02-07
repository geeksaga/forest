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
 * http://www.java2s.com/Tutorials/Java/JPA/4330__JPA_Query_Exists.htm
 * 
 * @author geeksaga
 * @version 0.1
 */
package com.geeksaga.forest.service;

import org.springframework.dao.DataAccessException;

import com.geeksaga.forest.entity.Seed;
import com.geeksaga.forest.entity.Tag;
import com.geeksaga.forest.entity.TagMap;

public interface TagMapService
{
    /**
     * Tag 를 파싱 한 후 등록 한다. 이미 등록된 Tag 인지 조회 하여 등록된 Tag 는 카운트만 증가 시키고 등록되어 있지 않으면 등록 시킨다.
     * 
     * 리턴 값은 등록한 태그의 첫 번째 태그 이다.
     * 
     * @param tags
     * @param type
     * @return
     * @throws DataAccessException
     */
    Tag add(Seed seed, Tag tags, TagMap.CNT_TYPE type);

    /**
     * Tag 를 파싱 한 후 등록 한다. 이미 등록된 Tag 인지 조회 하여 등록된 Tag 는 카운트만 증가 시키고, 등록되어 있지 않으면 등록 시킨 후 대상에 대한 Tag 내용을 수정 한다.
     * 
     * 리턴 값은 등록한 태그의 첫 번째 Tag 이다.
     * 
     * @param tags
     * @param type
     * @param update
     * @return
     * @throws DataAccessException
     */
    Tag add(Seed seed, Tag tags, TagMap.CNT_TYPE type, boolean update);
}