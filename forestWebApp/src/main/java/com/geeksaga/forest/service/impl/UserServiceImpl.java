package com.geeksaga.forest.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.geeksaga.forest.dao.UserDao;
import com.geeksaga.forest.repositories.entity.SecurityUser;
import com.geeksaga.forest.repositories.entity.User;
import com.geeksaga.forest.service.UserService;

/**
 * @author geeksaga
 * @version 0.1
 */
@Service
@Transactional
public class UserServiceImpl implements UserService
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
        return userDao.getAll();
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
