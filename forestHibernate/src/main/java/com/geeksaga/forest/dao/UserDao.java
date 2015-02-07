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

import com.geeksaga.forest.entity.SecurityUser;
import com.geeksaga.forest.entity.User;

public interface UserDao
{
    List<User> findAll();
    
    User findByName(String name);
    
    SecurityUser authenticate(SecurityUser securityUser);
}