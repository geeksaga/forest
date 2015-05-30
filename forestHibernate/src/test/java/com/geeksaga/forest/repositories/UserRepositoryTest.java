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
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.geeksaga.common.crypt.PasswordEncoderWrapper;
import com.geeksaga.common.util.KeyGenerator;
import com.geeksaga.forest.entity.Authority;
import com.geeksaga.forest.entity.QAuthority;
import com.geeksaga.forest.entity.QUser;
import com.geeksaga.forest.entity.Seed;
import com.geeksaga.forest.entity.User;
import com.geeksaga.forest.entity.UserPredicates;
import com.geeksaga.forest.enums.code.ROLE;
import com.geeksaga.forest.util.AbstractRepositoryTestSupport;
import com.mysema.query.Tuple;
import com.mysema.query.jpa.impl.JPAQuery;

public class UserRepositoryTest extends AbstractRepositoryTestSupport
{
    @Autowired
    private AuthorityRepository authorityRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SeedRepository seedRepository;

    @PersistenceContext
    private EntityManager entityManager;

    private User user;
    private Authority authority;

    private JPAQuery getQuery()
    {
        return new JPAQuery(entityManager);
    }

    @Before
    public void setup()
    {
        List<User> users = new ArrayList<>();

        user = new User(KeyGenerator.generateKeyToLong(), "geeksaga@geeksaga.com", PasswordEncoderWrapper.encode("password"), "jihun", "0");

        User user1 = new User(KeyGenerator.generateKeyToLong(), "geeksaga1@geeksaga.com", PasswordEncoderWrapper.encode("password"),
                "jihun", "1");

        User user2 = new User(KeyGenerator.generateKeyToLong(), "geeksaga2@geeksaga.com", PasswordEncoderWrapper.encode("password"),
                "jihun", "2");

        User user3 = new User(KeyGenerator.generateKeyToLong(), "geeksaga3@geeksaga.com", PasswordEncoderWrapper.encode("password"),
                "jihun", "3");

        users.add(user);
        users.add(user1);
        users.add(user2);
        users.add(user3);

        authority = new Authority(KeyGenerator.generateKeyToLong(), user.getSid(), ROLE.USER.getCode());
        Set<Authority> set = new HashSet<Authority>();
        set.add(authority);

        user.setAuthority(set);

        userRepository.save(users);
        userRepository.flush();

        Seed seed = new Seed(KeyGenerator.generateKeyToLong(), "Test 1", "Content 1", user.getSid());

        seedRepository.save(seed);
        seedRepository.flush();
    }

    @Test
    public void testSave()
    {
        User user = new User(KeyGenerator.generateKeyToLong(), "save@geeksaga.com", PasswordEncoderWrapper.encode("password"), "save",
                "user");

        Set<Authority> authorities = new HashSet<>();
        Authority authority = new Authority(KeyGenerator.generateKeyToLong(), user.getSid(), ROLE.USER.getCode());
        authorities.add(authority);

        // user.setAuthority(authorities);

        User savedUser = userRepository.saveAndFlush(user);

        assertNotNull(savedUser);

        assertThat(user.getSid(), is(savedUser.getSid()));
    }

    @Test
    public void testFindAll()
    {
        List<User> users = userRepository.findAll();

        assertThat(4, is(users.size()));

        Set<Authority> authorities = users.get(0).getAuthority();

        assertThat(authority.getRole(), is((authorities.toArray(new Authority[authorities.size()])[0]).getRole()));
    }

    @Test
    public void testFindUseEntityManager()
    {
        entityManager.clear();

        Set<Authority> authorities = entityManager.find(User.class, user.getSid()).getAuthority();

        assertThat(authority.getRole(), is((authorities.toArray(new Authority[authorities.size()])[0]).getRole()));
    }

    @Test
    public void testJoinFindAll()
    {
        entityManager.clear();

        // DTO
        // ConstructorExpression.create(QUser.class, QAuthority.authority.sid, QAuthority.authority.role)

        List<Tuple> tuple = getQuery().from(QUser.user).leftJoin(QUser.user.authority, QAuthority.authority)
                .list(QUser.user.email, QAuthority.authority.role);

        assertThat(4, is(tuple.size()));

        List<User> users = getQuery().from(QUser.user).leftJoin(QUser.user.authority, QAuthority.authority).list(QUser.user);

        assertThat(4, is(users.size()));

    }

    @Test
    public void testLeftJoinFindByFirstName()
    {
        List<User> users = getQuery().from(QUser.user).leftJoin(QUser.user.authority, QAuthority.authority)
                .where(QUser.user.firstName.eq("jihun")).list(QUser.user);

        assertThat(4, is(users.size()));
    }

    @Ignore
    @Test
    public void testFindListForSeedByUser()
    {
        // entityManager.clear();
        //
        // List<User> users = getQuery().from(QUser.user).leftJoin(QUser.user.seeds,
        // QSeed.seed).where(QUser.user.sid.eq(QSeed.seed.user.sid))
        // .list(QUser.user);
        //
        // System.out.println(users.get(0));
    }

    @Test
    public void testFindByFirstName()
    {
        List<User> users = (List<User>) userRepository.findAll(UserPredicates.firstNameLike("jihun"));

        assertThat(4, is(users.size()));
    }

    @Test
    public void testFindOne()
    {
        assertThat("geeksaga@geeksaga.com", is(userRepository.findOne(QUser.user.email.eq("geeksaga@geeksaga.com")).getEmail()));
    }
}