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
package com.geeksaga.forest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import com.geeksaga.forest.repositories.entity.Tag;

public interface TagRepository extends JpaRepository<Tag, Long>, QueryDslPredicateExecutor<Tag>
{
    @Modifying(clearAutomatically = true)
    @Query("UPDATE Tag t SET t.hitCnt = (t.hitCnt + 1) WHERE t.tagName=:tagName")
    int updateCnt(@Param("tagName") String tagName);

    Tag findByTagName(String tagName);
}