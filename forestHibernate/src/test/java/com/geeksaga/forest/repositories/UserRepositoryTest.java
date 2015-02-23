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

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.geeksaga.common.crypt.PasswordEncoderWrapper;
import com.geeksaga.common.util.KeyGenerator;
import com.geeksaga.forest.entity.QUser;
import com.geeksaga.forest.entity.User;
import com.geeksaga.forest.entity.UserManager;
import com.geeksaga.forest.entity.UserPredicates;
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

        User user = new User(KeyGenerator.generateKeyToLong(), "geeksaga@geeksaga.com", PasswordEncoderWrapper.encode("password"), "jihun", "0");
        user.setUserManager(userManager);

        User user1 = new User(KeyGenerator.generateKeyToLong(), "geeksaga1@geeksaga.com", PasswordEncoderWrapper.encode("password"), "jihun", "1");
        user1.setUserManager(userManager);

        User user2 = new User(KeyGenerator.generateKeyToLong(), "geeksaga2@geeksaga.com", PasswordEncoderWrapper.encode("password"), "jihun", "2");
        user2.setUserManager(userManager);

        User user3 = new User(KeyGenerator.generateKeyToLong(), "geeksaga3@geeksaga.com", PasswordEncoderWrapper.encode("password"), "jihun", "3");
        user3.setUserManager(userManager);

        users.add(user);
        users.add(user1);
        users.add(user2);
        users.add(user3);

        userRepository.save(users);
    }

    @Test
    public void testSave()
    {
        User user = new User(KeyGenerator.generateKeyToLong(), "save@geeksaga.com", PasswordEncoderWrapper.encode("password"), "save", "user");
        user.setUserManager(savedUserManager);

        User savedUser = userRepository.save(user);

        assertNotNull(savedUser);
        assertEquals(user.getSid(), savedUser.getSid());
    }

    @Test
    public void testFindAll()
    {
        List<User> users = (List<User>) userRepository.findAll();

        assertEquals(4, users.size());
    }

    @Test
    public void testFindByFirstName()
    {
        List<User> users = (List<User>) userRepository.findAll(UserPredicates.firstNameLike("jihun"));
        
        assertEquals(4, users.size());
    }

    @Test
    public void testFindByUserManager()
    {
        List<User> users = (List<User>) userRepository.findByUserManager(savedUserManager);

        assertEquals(4, users.size());
    }
    
    @Test
    public void testFindOne()
    {
        assertThat("geeksaga@geeksaga.com", is(userRepository.findOne(QUser.user.email.eq("geeksaga@geeksaga.com")).getEmail()));
    }
}