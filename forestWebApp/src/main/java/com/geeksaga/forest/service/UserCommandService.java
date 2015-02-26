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

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.geeksaga.common.util.KeyGenerator;
import com.geeksaga.forest.Profiles;
import com.geeksaga.forest.entity.Authentication;
import com.geeksaga.forest.entity.Authority;
import com.geeksaga.forest.entity.QUser;
import com.geeksaga.forest.entity.SecurityUser;
import com.geeksaga.forest.entity.User;
import com.geeksaga.forest.enums.code.ROLE;
import com.geeksaga.forest.repositories.UserRepository;

@Profile(Profiles.PRODUCTION)
@Service
public class UserCommandService extends AbstractSpringData<User>
{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthorityService authorityService;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserCommandService()
    {
        super(User.class);
    }
    
    public UserCommandService(Class<User> clazz)
    {
        super(clazz);
    }

    /**
     * 새로운 사용자를 등록한다.
     * 
     * 새로운 사용자가 등록될 경우 기본적으로 계정은 비활서화 상태로 두고 email 인증을 통해 활성화 한다.
     * 기본적인 권한은 ANONYMOUS 권한을 부여 한다.
     * email 인증이 성공하면 USER 권한을 새로 부여 한다.
     * 
     * @param user
     * @return
     * @see Authentication Entity
     */
    @Transactional
    public User save(User user)
    {
        user.setSid(KeyGenerator.generateKeyToLong());
        user.setEnabled(false);
        user.setAccountNonExpired(true);
        user.setCredentialsNonExpired(true);
        user.setAccountNonLocked(true);

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        user = userRepository.save(user);

        if (user != null)
        {
            Authority authority = new Authority(user.getSid(), ROLE.ANONYMOUS.getCode());
            
            authorityService.save(authority);
        }

        return user;
    }

    @Transactional(readOnly = true)
    public SecurityUser authenticate(SecurityUser securityUser)
    {
        User user = userRepository.findOne(QUser.user.email.eq(securityUser.getEmail()));
        
        SecurityUser authenrityUser = new SecurityUser(user);

        if (user == null)
        {
            return securityUser;
        }

        List<Authority> authorityList = authorityService.findByUser(authenrityUser);
        
        List<SimpleGrantedAuthority> grantedAuthority = new ArrayList<SimpleGrantedAuthority>();
        
        if(authorityList != null)
        {
            for(Authority authority : authorityList)
            {
                grantedAuthority.add(new SimpleGrantedAuthority(authority.getRole()));
            }
        }
        else
        {
            grantedAuthority.add(new SimpleGrantedAuthority(ROLE.ANONYMOUS.getCode()));
        }
        
        authenrityUser.setAuthorities(grantedAuthority);

        return authenrityUser;
    }
}