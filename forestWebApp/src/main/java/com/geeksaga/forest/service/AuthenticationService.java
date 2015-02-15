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

import com.geeksaga.forest.entity.Authentication;
import com.geeksaga.forest.repositories.AuthenticationRepository;

@Service
public class AuthenticationService extends AbstractSpringData<Authentication>
{
    private static final Log logger = LogFactory.getLog(AuthenticationService.class);

    @Autowired
    protected AuthenticationRepository authenticationRepository;
    
    public AuthenticationService()
    {
        super(Authentication.class);
    }
    
    @Transactional
    public Authentication save(Authentication authentication)
    {
        return authentication;
    }
}