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

import org.hibernate.Session;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.geeksaga.common.crypt.PasswordEncoderWrapper;
import com.geeksaga.common.util.DateConvertor;
import com.geeksaga.common.util.KeyGenerator;
import com.geeksaga.forest.entity.Authority;
import com.geeksaga.forest.entity.QAuthority;
import com.geeksaga.forest.entity.QSeed;
import com.geeksaga.forest.entity.QUser;
import com.geeksaga.forest.entity.User;
import com.geeksaga.forest.entity.UserManager;
import com.geeksaga.forest.entity.UserPredicates;
import com.geeksaga.forest.enums.code.ROLE;
import com.geeksaga.forest.util.AbstractRepositoryTestSupport;
import com.google.common.collect.Lists;
import com.mysema.query.Tuple;
import com.mysema.query.jpa.hibernate.HibernateQuery;
import com.mysema.query.jpa.hibernate.sql.HibernateSQLQuery;
import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.sql.H2Templates;
import com.mysema.query.sql.SQLTemplates;

public class UserRepositoryTest extends AbstractRepositoryTestSupport
{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserManagerRepository userManagerRepository;

    private UserManager savedUserManager;

    @PersistenceContext
    private EntityManager entityManager;

    private Session getSession()
    {
        return (Session) entityManager.getDelegate();
    }

    private HibernateQuery getSelectQuery()
    {
        return new HibernateQuery(getSession());
    }

    private JPAQuery getSelectQuery3()
    {
        return new JPAQuery(entityManager);
    }

    private HibernateSQLQuery getSelectQuery2()
    {
        SQLTemplates templates = new H2Templates();

        return new HibernateSQLQuery(getSession(), templates);
    }

    @Before
    public void setup()
    {
        userManagerRepository.deleteAll();

        UserManager userManager = new UserManager();
        userManager.setSid(KeyGenerator.generateKeyToLong());
        userManager.setName("manager");

        savedUserManager = userManagerRepository.save(userManager);

        List<User> users = Lists.newArrayList();

        User user = new User(KeyGenerator.generateKeyToLong(), "geeksaga@geeksaga.com", PasswordEncoderWrapper.encode("password"), "jihun",
                "0");
        user.setUserManager(userManager);

        User user1 = new User(KeyGenerator.generateKeyToLong(), "geeksaga1@geeksaga.com", PasswordEncoderWrapper.encode("password"),
                "jihun", "1");
        user1.setUserManager(userManager);

        User user2 = new User(KeyGenerator.generateKeyToLong(), "geeksaga2@geeksaga.com", PasswordEncoderWrapper.encode("password"),
                "jihun", "2");
        user2.setUserManager(userManager);

        User user3 = new User(KeyGenerator.generateKeyToLong(), "geeksaga3@geeksaga.com", PasswordEncoderWrapper.encode("password"),
                "jihun", "3");
        user3.setUserManager(userManager);

        Authority authority = new Authority(KeyGenerator.generateKeyToLong(), user.getSid(), ROLE.USER.getCode());
        authority.setRegistTimestamp(DateConvertor.getDateTimeFormat());

        Set<Authority> set = new HashSet<Authority>();
        set.add(authority);

        user.setAuthority(set);

        users.add(user);
        users.add(user1);
        users.add(user2);
        users.add(user3);

        userRepository.save(users);
    }

    @Test
    public void testSave()
    {
        User user = new User(KeyGenerator.generateKeyToLong(), "save@geeksaga.com", PasswordEncoderWrapper.encode("password"), "save",
                "user");
        user.setUserManager(savedUserManager);

        Set<Authority> authorities = new HashSet<>();
        Authority authority = new Authority(KeyGenerator.generateKeyToLong(), user.getSid(), ROLE.USER.getCode());
        authority.setRegistTimestamp(DateConvertor.getDateTimeFormat());
        authorities.add(authority);

        user.setAuthority(authorities);

        User savedUser = userRepository.save(user);

        assertNotNull(savedUser);
        assertEquals(user.getSid(), savedUser.getSid());
    }

    @Test
    public void testFindAll()
    {
        List<User> users = (List<User>) userRepository.findAll();

        assertEquals(4, users.size());

        System.out.println(users.get(0).getAuthority());
    }

    @Test
    public void testJoinFindAll()
    {
        List<User> users = getSelectQuery().from(QUser.user).leftJoin(QUser.user.authority, QAuthority.authority)
                .where(QUser.user.firstName.eq("jihun")).list(QUser.user);

        // DTO
        // ConstructorExpression.create(QUser.class, QAuthority.authority.sid, QAuthority.authority.role)

        // List<Tuple> users2 = getSelectQuery2().from(QUser.user).where(QUser.user.firstName.eq("jihun"))
        // .list(QUser.user.firstName, QUser.user.lastName);
        // System.out.println(users2.get(0));

        assertEquals(4, users.size());

        System.out.println(users.get(0).getAuthority());
        
        List<User> users3 = getSelectQuery3().from(QUser.user).leftJoin(QUser.user.authority, QAuthority.authority)
                .where(QUser.user.firstName.eq("jihun")).list(QUser.user);
    }

    @Test
    public void testFindListForSeedByUser()
    {
        // List<User> users = getSelectQuery().from(QUser.user).leftJoin(QUser.user.seeds, QSeed.seed)
        // .where(QUser.user.sid.eq(QSeed.seed.user.sid)).list(QUser.user);

        // System.out.println(users.get(0).getSeed());
    }

    @Test
    public void testFindByFirstName()
    {
        List<User> users = (List<User>) userRepository.findAll(UserPredicates.firstNameLike("jihun"));

        assertEquals(4, users.size());
    }

    @Test
    public void testFindByUserManager()
    {
        List<User> users = (List<User>) userRepository.findByUserManager(savedUserManager);

        assertEquals(4, users.size());
    }

    @Test
    public void testFindOne()
    {
        assertThat("geeksaga@geeksaga.com", is(userRepository.findOne(QUser.user.email.eq("geeksaga@geeksaga.com")).getEmail()));
    }
}