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

import com.geeksaga.forest.entity.UserManager;

public interface UserManagerRepository extends JpaRepository<UserManager, Long>
{}