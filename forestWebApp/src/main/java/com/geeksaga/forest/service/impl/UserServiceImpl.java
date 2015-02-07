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
package com.geeksaga.forest.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.geeksaga.forest.dao.UserDao;
import com.geeksaga.forest.entity.SecurityUser;
import com.geeksaga.forest.entity.User;
import com.geeksaga.forest.service.UserQueryService;

@Service
@Transactional
public class UserServiceImpl implements UserQueryService
{
    @Autowired
    private UserDao userDao;

    // @Autowired
    // private HistoryDao historyDao;

    public UserServiceImpl()
    {}

    /*
     * (non-Javadoc)
     * 
     * @see com.geeksaga.forest.service.UserService#listup()
     */
    @Transactional(readOnly = true)
    public List<User> listup()
    {
        return userDao.findAll();
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.geeksaga.forest.service.UserService#authenticate(com.geeksaga.forest.repositories.entity.SecurityUser)
     */
    public SecurityUser authenticate(SecurityUser securityUser)
    {
        return userDao.authenticate(securityUser);
    }

    // @Transactional(readOnly = true)
    // @Override
    // public List<History> getHistories(int userId)
    // {
    // return null;
    // }
}
