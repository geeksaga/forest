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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.geeksaga.common.crypt.PasswordEncoderWrapper;
import com.geeksaga.common.util.KeyGenerator;
import com.geeksaga.forest.entity.User;
import com.geeksaga.forest.entity.UserManager;
import com.geeksaga.forest.entity.UserPredicates;
import com.geeksaga.forest.repositories.UserManagerRepository;
import com.geeksaga.forest.repositories.UserRepository;
import com.geeksaga.forest.util.AbstractRepositoryTestSupport;
import com.google.common.collect.Lists;

public class UserRepositoryTest extends AbstractRepositoryTestSupport
{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserManagerRepository userManagerRepository;

    private UserManager savedUserManager;

    @Before
    public void setup()
    {
        userManagerRepository.deleteAll();
        
        UserManager userManager = new UserManager();
        userManager.setSid(KeyGenerator.generateKeyToLong());
        userManager.setName("manager");

        savedUserManager = userManagerRepository.save(userManager);

        List<User> users = Lists.newArrayList();

        User user = new User();
        user.setSid(KeyGenerator.generateKeyToLong());
        user.setName("geeksaga");
        user.setEmail("geeksaga@geeksaga.com");
        user.setPassword(PasswordEncoderWrapper.encode("password"));

        user.setUserManager(userManager);

        User user1 = new User();
        user1.setSid(KeyGenerator.generateKeyToLong());
        user1.setName("geeksaga1");
        user1.setEmail("geeksaga1@geeksaga.com");
        user1.setPassword(PasswordEncoderWrapper.encode("password"));
        user1.setUserManager(userManager);

        User user2 = new User();
        user2.setSid(KeyGenerator.generateKeyToLong());
        user2.setName("geeksaga2");
        user2.setEmail("geeksaga2@geeksaga.com");
        user2.setPassword(PasswordEncoderWrapper.encode("password"));
        user2.setUserManager(userManager);

        User user3 = new User();
        user3.setSid(KeyGenerator.generateKeyToLong());
        user3.setName("geeksaga3");
        user3.setEmail("geeksaga3@geeksaga.com");
        user3.setPassword(PasswordEncoderWrapper.encode("password"));
        user3.setUserManager(userManager);

        users.add(user);
        users.add(user1);
        users.add(user2);
        users.add(user3);

        userRepository.save(users);
    }

    @Test
    public void save()
    {
        User user = new User();
        user.setSid(KeyGenerator.generateKeyToLong());
        user.setName("save");
        user.setEmail("save@geeksaga.com");
        user.setPassword(PasswordEncoderWrapper.encode("password"));

        user.setUserManager(savedUserManager);

        User savedUser = userRepository.save(user);

        assertNotNull(savedUser);
        assertEquals(user.getSid(), savedUser.getSid());
    }

    @Test
    public void findAll()
    {
        List<User> users = (List<User>) userRepository.findAll();

        assertEquals(4, users.size());
    }

    @Test
    public void findByName()
    {
        List<User> users = (List<User>) userRepository.findAll(UserPredicates.nameLike("geeksaga"));
        
        assertEquals(4, users.size());
    }

    @Test
    public void findByUserManager()
    {
        List<User> users = (List<User>) userRepository.findByUserManager(savedUserManager);

        assertEquals(4, users.size());
    }
}