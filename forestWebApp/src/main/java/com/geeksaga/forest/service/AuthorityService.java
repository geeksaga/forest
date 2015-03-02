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

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.geeksaga.common.util.KeyGenerator;
import com.geeksaga.forest.entity.Authentication;
import com.geeksaga.forest.entity.Authority;
import com.geeksaga.forest.entity.User;
import com.geeksaga.forest.repositories.AuthorityRepository;

@Service
public class AuthorityService extends AbstractSpringData<Authority>
{
    @Autowired
    protected AuthorityRepository authorityRepository;

    public AuthorityService()
    {
        super(Authentication.class);
    }

    @Transactional
    public Authority save(Authority authority)
    {
        authority.setSid(KeyGenerator.generateKeyToLong());
        authority.setRegistTimestamp(new Date());

        authorityRepository.save(authority);

        return authority;
    }

    public List<Authority> findByUser(User user)
    {
         List<Authority> list = authorityRepository.findByUserSid(user.getSid());
        // List<Authority> list = authorityRepository.findByUser(AuthorityPredicates.userSid(user.getSid()));

        return list;
    }
}