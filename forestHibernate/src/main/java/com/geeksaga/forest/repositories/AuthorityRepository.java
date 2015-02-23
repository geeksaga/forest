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

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.geeksaga.forest.entity.Authority;

public interface AuthorityRepository extends JpaRepository<Authority, Long>, QueryDslPredicateExecutor<Authority>
{
    List<Authority> findByUserSid(Long userSid);

    // List<Authority> findByUser(Predicate predicate);
}