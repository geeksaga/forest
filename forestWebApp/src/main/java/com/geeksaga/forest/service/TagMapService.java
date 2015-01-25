package com.geeksaga.forest.service;

import org.springframework.dao.DataAccessException;

import com.geeksaga.forest.repositories.entity.Tag;
import com.geeksaga.forest.repositories.entity.TagMap;

/**
 * @author geeksaga
 * @version 0.1
 */
public interface TagMapService
{
    /**
     * Tag 를 파싱 한 후 등록 한다.
     * 이미 등록된 Tag 인지 조회 하여 등록된 Tag 는 카운트만 증가 시키고 등록되어 있지 않으면 등록 시킨다.
     * 
     * 리턴 값은 등록한 태그의 첫 번째 태그 이다.
     * 
     * @param tags
     * @param type
     * @return
     * @throws DataAccessException
     */
    Tag add(Tag tags, TagMap.CNT_TYPE type);
        
    /**
     * Tag 를 파싱 한 후 등록 한다.
     * 이미 등록된 Tag 인지 조회 하여 등록된 Tag 는 카운트만 증가 시키고,
     * 등록되어 있지 않으면 등록 시킨 후 대상에 대한 Tag 내용을 수정 한다.
     * 
     * 리턴 값은 등록한 태그의 첫 번째 Tag 이다.
     * 
     * @param tags
     * @param type
     * @param update
     * @return
     * @throws DataAccessException
     */
    Tag add(Tag tags, TagMap.CNT_TYPE type, boolean update);
}