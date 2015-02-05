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

import com.geeksaga.forest.repositories.entity.TagMap;

public interface TagMapDao
{
    /**
     * 태그를 등록 한다.
     * 
     * @param tagMap
     * @return
     */
    TagMap save(TagMap tagMap);
    
    List<TagMap> save(Iterable<TagMap> list);
    
    /**
     * 이미  등록되어 있는 태그 인지 확인 한다.
     * 
     * @param tagMap
     * @return
     */
    boolean exists(TagMap.PK tagMap);
}