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
package com.geeksaga.forest.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.memory.UserAttribute;
import org.springframework.stereotype.Repository;

import com.geeksaga.forest.dao.UserDao;
import com.geeksaga.forest.entity.QUser;
import com.geeksaga.forest.entity.SecurityUser;
import com.geeksaga.forest.entity.User;
import com.geeksaga.forest.repositories.UserRepository;
import com.geeksaga.forest.repositories.security.UserGrantedAuthority;

@Repository
public class UserDaoImpl extends AbstractSpringData<User> implements UserDao
{
    @Autowired
    private UserRepository userRepository;

    public UserDaoImpl()
    {
        super(User.class);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.geeksaga.forest.dao.UserDao#findAll()
     */
    public List<User> findAll()
    {
        return userRepository.findAll();
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.geeksaga.forest.dao.UserDao#findByName(java.lang.String)
     */
    public User findByName(String name)
    {
        return userRepository.findOne(QUser.user.name.eq(name));
        // return userRepository.findByName(name);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.geeksaga.forest.dao.UserDao#authenticate(com.geeksaga.forest.repositories.entity.SecurityUser)
     */
    public SecurityUser authenticate(SecurityUser securityUser)
    {
        SecurityUser authenrityUser = (SecurityUser) userRepository.findOne(QUser.user.email.eq(securityUser.getEmail()));

        UserAttribute userAttributes = new UserAttribute();

        if (authenrityUser == null || !securityUser.getPassword().equals(authenrityUser.getPassword()))
        {
            return securityUser;
        }

        UserGrantedAuthority authority = new UserGrantedAuthority(authenrityUser.getAuthority());

        if (userAttributes.getAuthorities().contains(authority))
        {
            int index = userAttributes.getAuthorities().indexOf(authority);
            authority = (UserGrantedAuthority) userAttributes.getAuthorities().get(index);
        }
        else
        {
            userAttributes.addAuthority(authority);
        }

        authenrityUser.setAuthorities(userAttributes.getAuthorities());

        return authenrityUser;
    }
}