package com.geeksaga.forest.service.impl;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.geeksaga.common.crypt.PasswordEncoderWrapper;
import com.geeksaga.common.util.KeyGenerator;
import com.geeksaga.forest.AbstractTestSupport;
import com.geeksaga.forest.dao.UserDao;
import com.geeksaga.forest.repositories.entity.User;
import com.geeksaga.forest.service.UserService;

/**
 * @author geeksaga
 * @version 0.1
 */
public class UserServiceImplTest extends AbstractTestSupport
{
    @Autowired
    private UserService userService;
    @Autowired
    private UserDao userDao;

    // @Autowired
    // private HistoryDao historyDao;

    @Before
    public void setUp()
    {
        userDao.deleteAll();

        addTestData();
    }

    private void addTestData()
    {
        User point99User = new User();
        // point99User.setPoint(99);
        point99User.setSid(KeyGenerator.generateKeyToLong());
        point99User.setName("point99");
        point99User.setEmail("geeksaga99@geeksaga.com");
        point99User.setPassword(PasswordEncoderWrapper.encode("password"));
        // point99User.setLevel(UserLevel.NORMAL);
        userDao.add(point99User);

        User point299User = new User();
        point299User.setSid(KeyGenerator.generateKeyToLong());
        // point299User.setPoint(299);
        point299User.setName("point199");
        point299User.setEmail("geeksaga199@geeksaga.com");
        point299User.setPassword(PasswordEncoderWrapper.encode("password"));
        // point299User.setLevel(UserLevel.MASTER);
        userDao.add(point299User);

        User point301User = new User();
        // point301User.setPoint(301);
        point301User.setSid(KeyGenerator.generateKeyToLong());
        point301User.setName("point299");
        point301User.setEmail("geeksaga299@geeksaga.com");
        point301User.setPassword(PasswordEncoderWrapper.encode("password"));
        // point301User.setLevel(UserLevel.MVP);
        userDao.add(point301User);
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