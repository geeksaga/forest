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

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.geeksaga.common.crypt.PasswordEncoderWrapper;
import com.geeksaga.common.util.KeyGenerator;
import com.geeksaga.forest.AbstractTestSupport;
import com.geeksaga.forest.common.util.Logger;
import com.geeksaga.forest.entity.User;
import com.geeksaga.forest.repositories.UserRepository;

public class UserServiceTest extends AbstractTestSupport
{
    @Autowired
    private UserQueryService userQueryService;
    @Autowired
    private UserRepository userRepository;

    // @Autowired
    // private HistoryDao historyDao;

    @Before
    public void setUp()
    {
        userRepository.deleteAll();

        addTestData();
    }

    private void addTestData()
    {
        List<User> users = new ArrayList<>();
        
        users.add(new User(KeyGenerator.generateKeyToLong(), "geeksaga99@geeksaga.com", PasswordEncoderWrapper.encode("password"), "jihun", "jeon"));
        users.add(new User(KeyGenerator.generateKeyToLong(), "geeksaga199@geeksaga.com", PasswordEncoderWrapper.encode("password"), "jihun", "jeon"));
        users.add(new User(KeyGenerator.generateKeyToLong(), "geeksaga299@geeksaga.com", PasswordEncoderWrapper.encode("password"), "jihun", "jeon"));

        userRepository.save(users);
        userRepository.flush();
    }

    @Test
    public void testFindAll() throws Exception
    {
        List<User> listup = userQueryService.findAll();
        for (User user : listup)
        {
            Logger.info(user.toString());
        }
    }

    @Test
    public void testGetHistories() throws Exception
    {}

    @Test
    public void testSetUserLevelService() throws Exception
    {}
}