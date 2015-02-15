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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.geeksaga.common.util.KeyGenerator;
import com.geeksaga.forest.entity.Authentication;
import com.geeksaga.forest.entity.Authority;
import com.geeksaga.forest.repositories.AuthorityRepository;

@Service
public class AuthorityService extends AbstractSpringData<Authority>
{
    private static final Log logger = LogFactory.getLog(AuthorityService.class);

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
        
        authorityRepository.save(authority);
        
        return authority;
    }
}