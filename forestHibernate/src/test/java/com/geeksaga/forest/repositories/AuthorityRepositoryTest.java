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
package com.geeksaga.forest.repositories;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.geeksaga.common.crypt.PasswordEncoderWrapper;
import com.geeksaga.common.util.KeyGenerator;
import com.geeksaga.forest.entity.Authority;
import com.geeksaga.forest.entity.QAuthority;
import com.geeksaga.forest.entity.QUser;
import com.geeksaga.forest.entity.User;
import com.geeksaga.forest.enums.code.ROLE;
import com.geeksaga.forest.util.AbstractRepositoryTestSupport;
import com.mysema.query.jpa.impl.JPAQuery;

public class AuthorityRepositoryTest extends AbstractRepositoryTestSupport
{
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private AuthorityRepository authorityRepository;

    @PersistenceContext
    private EntityManager entityManager;

    private JPAQuery getSelectQuery3()
    {
        return new JPAQuery(entityManager);
    }

    private User user;

    @Before
    public void setup()
    {
        user = new User(KeyGenerator.generateKeyToLong(), "geeksaga@geeksaga.com", PasswordEncoderWrapper.encode("password"), "jihun", "0");

        Authority authority = new Authority(KeyGenerator.generateKeyToLong(), user.getSid(), ROLE.USER.getCode());

        Set<Authority> set = new HashSet<Authority>();
        set.add(authority);

        user.setAuthority(set);

        userRepository.save(user);
        
//        authorityRepository.save(authority);
    }

    @Test
    public void testSave()
    {
        Set<Authority> authorities = new HashSet<>();
        Authority authority = new Authority(KeyGenerator.generateKeyToLong(), user.getSid(), ROLE.USER.getCode());
        authorities.add(authority);

        // user.setAuthority(authorities);

        Authority savedUser = authorityRepository.save(authority);

        assertNotNull(savedUser);
        assertEquals(user.getSid(), authority.getUser().getSid());
    }

    @Test
    public void testFindAll()
    {
        List<Authority> users = (List<Authority>) authorityRepository.findAll();

        // assertEquals(4, users.size());

        System.out.println(users);
    }

    @Test
    public void testJoinFindAll()
    {
        System.out.println(getSelectQuery3().from(QUser.user).leftJoin(QUser.user.authority, QAuthority.authority)
                .list(QUser.user.email, QAuthority.authority.role));
        // .where(QUser.user.firstName.eq("jihun")).list(QUser.user);

        // DTO
        // ConstructorExpression.create(QUser.class, QAuthority.authority.sid, QAuthority.authority.role)

        // List<Tuple> users2 = getSelectQuery2().from(QUser.user).where(QUser.user.firstName.eq("jihun"))
        // .list(QUser.user.firstName, QUser.user.lastName);
        // System.out.println(users2.get(0));

        // assertEquals(4, users.size());

        // System.out.println(users.get(0).getAuthority().toArray()[0]);

        List<User> users3 = getSelectQuery3().from(QUser.user).leftJoin(QUser.user.authority, QAuthority.authority)
                .where(QUser.user.firstName.eq("jihun")).list(QUser.user);
    }

    @Test
    public void testFindOne()
    {
        assertThat(ROLE.USER.getCode(), is(authorityRepository.findOne(QAuthority.authority.user.sid.eq(user.getSid())).getRole()));
    }
}