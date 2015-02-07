package com.geeksaga.forest.service.impl;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.geeksaga.common.crypt.PasswordEncoderWrapper;
import com.geeksaga.common.util.KeyGenerator;
import com.geeksaga.forest.AbstractTestSupport;
import com.geeksaga.forest.dao.UserDao;
import com.geeksaga.forest.entity.User;
import com.geeksaga.forest.repositories.UserRepository;
import com.geeksaga.forest.service.UserQueryService;

/**
 * @author geeksaga
 * @version 0.1
 */
public class UserServiceImplTest extends AbstractTestSupport
{
    @Autowired
    private UserQueryService userService;
    @Autowired
    private UserDao userDao;
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
        User user1 = new User();
        // user1.setPoint(99);
        user1.setSid(KeyGenerator.generateKeyToLong());
        user1.setName("point99");
        user1.setEmail("geeksaga99@geeksaga.com");
        user1.setPassword(PasswordEncoderWrapper.encode("password"));
        // user1.setLevel(UserLevel.NORMAL);
        userRepository.saveAndFlush(user1);

        User user2 = new User();
        user2.setSid(KeyGenerator.generateKeyToLong());
        // user2.setPoint(299);
        user2.setName("point199");
        user2.setEmail("geeksaga199@geeksaga.com");
        user2.setPassword(PasswordEncoderWrapper.encode("password"));
        // user2.setLevel(UserLevel.MASTER);
        userRepository.saveAndFlush(user2);

        User user3 = new User();
        // user3.setPoint(301);
        user3.setSid(KeyGenerator.generateKeyToLong());
        user3.setName("point299");
        user3.setEmail("geeksaga299@geeksaga.com");
        user3.setPassword(PasswordEncoderWrapper.encode("password"));
        // user3.setLevel(UserLevel.MVP);
        userRepository.saveAndFlush(user3);
    }

    @Test
    public void testListup() throws Exception
    {
        List<User> listup = userService.listup();
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