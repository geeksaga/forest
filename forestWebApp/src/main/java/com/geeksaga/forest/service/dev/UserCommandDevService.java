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
package com.geeksaga.forest.service.dev;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.geeksaga.common.util.KeyGenerator;
import com.geeksaga.forest.Profiles;
import com.geeksaga.forest.entity.Authority;
import com.geeksaga.forest.entity.User;
import com.geeksaga.forest.enums.code.ROLE;
import com.geeksaga.forest.repositories.UserRepository;
import com.geeksaga.forest.service.AuthorityService;
import com.geeksaga.forest.service.UserCommandService;

@Profile(Profiles.DEV)
@Service
public class UserCommandDevService extends UserCommandService
{
    private static final Logger logger = LoggerFactory.getLogger(UserCommandDevService.class);
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthorityService authorityService;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserCommandDevService()
    {
        super(User.class);
    }

    /* (non-Javadoc)
     * @see com.geeksaga.forest.service.UserCommandService#save(com.geeksaga.forest.entity.User)
     */
    @Transactional
    public User save(User user)
    {
        user.setSid(KeyGenerator.generateKeyToLong());
        user.setEnabled(true);
        user.setAccountNonExpired(true);
        user.setCredentialsNonExpired(true);
        user.setAccountNonLocked(true);

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        user = userRepository.save(user);

        if (user != null)
        {
            authorityService.save(new Authority(user.getSid(), ROLE.USER.getCode()));
            authorityService.save(new Authority(user.getSid(), ROLE.ADMIN.getCode()));
        }

        logger.info(user.toString());
        
        return user;
    }
}