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
import com.geeksaga.forest.entity.UserManager;

public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User>, QueryDslPredicateExecutor<User>
{
    Page<User> findByUserManagerSid(Long userManagerSid, Pageable pageable);

    List<User> findByUserManagerSid(Long userManagerSid, Sort sort);

    List<User> findByUserManager(UserManager userManager);
}