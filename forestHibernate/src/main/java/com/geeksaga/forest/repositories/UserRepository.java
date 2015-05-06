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

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.geeksaga.forest.entity.User;

public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User>, QueryDslPredicateExecutor<User>
{
    // @Query(value = "SELECT A, B FROM User A, Authority B WHERE A.sid = B.user.sid")
    // @Query(value = "SELECT A FROM User A")
    // List<User> findAll();

    Page<User> findBySid(Long sid, Pageable pageable);

    List<User> findBySid(Long sid, Sort sort);
}