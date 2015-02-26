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

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.geeksaga.common.crypt.PasswordEncoderWrapper;
import com.geeksaga.common.util.KeyGenerator;
import com.geeksaga.forest.AbstractTestSupport;
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
        User user1 = new User(KeyGenerator.generateKeyToLong(), "geeksaga99@geeksaga.com", PasswordEncoderWrapper.encode("password"), "jihun", "jeon");
        // user1.setPoint(99);
        // user1.setLevel(UserLevel.NORMAL);
        userRepository.saveAndFlush(user1);

        User user2 = new User(KeyGenerator.generateKeyToLong(), "geeksaga199@geeksaga.com", PasswordEncoderWrapper.encode("password"), "jihun", "jeon");
        // user2.setPoint(299);
        // user2.setLevel(UserLevel.MASTER);
        userRepository.saveAndFlush(user2);

        User user3 = new User(KeyGenerator.generateKeyToLong(), "geeksaga299@geeksaga.com", PasswordEncoderWrapper.encode("password"), "jihun", "jeon");
        // user3.setPoint(301);
        // user3.setLevel(UserLevel.MVP);
        userRepository.saveAndFlush(user3);
    }

    @Test
    public void testFindAll() throws Exception
    {
        List<User> listup = userQueryService.findAll();
        for (User user : listup)
        {
            System.out.println(user);
        }
    }

    @Test
    public void testGetHistories() throws Exception
    {}

    @Test
    public void testSetUserLevelService() throws Exception
    {}
}