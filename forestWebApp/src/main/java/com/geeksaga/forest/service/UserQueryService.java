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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.geeksaga.forest.entity.QUser;
import com.geeksaga.forest.entity.User;
import com.geeksaga.forest.repositories.UserRepository;

@Service
public class UserQueryService
{
    @Autowired
    private UserRepository userRepository;
    
    @Transactional(readOnly = true)
    public List<User> findAll()
    {
        return userRepository.findAll();
    }
    
    public User findByName(String name)
    {
        return userRepository.findOne(QUser.user.name.eq(name));
    }
    
    // List<History> getHistories(int userId);
    // void setUserLevelService(UserLevelService userLevelService);
}
