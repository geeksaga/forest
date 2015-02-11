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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.memory.UserAttribute;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.geeksaga.common.util.KeyGenerator;
import com.geeksaga.forest.entity.QUser;
import com.geeksaga.forest.entity.SecurityUser;
import com.geeksaga.forest.entity.User;
import com.geeksaga.forest.repositories.UserRepository;
import com.geeksaga.forest.security.UserGrantedAuthority;

@Service
public class UserCommandService extends AbstractSpringData<User>
{
    @Autowired
    private UserRepository userRepository;
    
    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserCommandService()
    {
        super(User.class);
    }

    @Transactional
    public User save(User user)
    {
        user.setSid(KeyGenerator.generateKeyToLong());
        user.setEnabled(false);
        user.setAccountNonExpired(true);
        user.setCredentialsNonExpired(true);
        user.setAccountNonLocked(true);

        System.out.println(user);

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userRepository.save(user);

        return user;
    }

    @Transactional(readOnly = true)
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