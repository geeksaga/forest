package com.geeksaga.forest.repositories.entity;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.geeksaga.common.crypt.PasswordEncoderWrapper;
import com.geeksaga.common.util.KeyGenerator;
import com.geeksaga.forest.config.DataConfig;
import com.geeksaga.forest.repositories.UserManagerRepository;
import com.google.common.collect.Lists;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DataConfig.class)
public class UserManagerRepositoryTest //extends AbstractRepositoryTestSupport
{
    @Autowired
    private UserManagerRepository userManagerRepository;

    @Test
    public void save()
    {
        UserManager userManager = new UserManager();
        userManager.setSid(KeyGenerator.generateKeyToLong());
        userManager.setName("new_manager");

        List<User> users = Lists.newArrayList();
        userManager.setUsers(users);

        User user1 = new User();
        user1.setSid(KeyGenerator.generateKeyToLong());
        user1.setName("geeksaga5");
        user1.setEmail("geeksaga5@geeksaga.com");
        user1.setPassword(PasswordEncoderWrapper.encode("password"));
        user1.setUserManager(userManager);

        User user2 = new User();
        user2.setSid(KeyGenerator.generateKeyToLong());
        user2.setName("geeksaga6");
        user2.setEmail("geeksaga6@geeksaga.com");
        user2.setPassword(PasswordEncoderWrapper.encode("password"));
        user2.setUserManager(userManager);

        users.add(user1);
        users.add(user2);

        UserManager savedUserManager = userManagerRepository.save(userManager);

        assertNotNull(savedUserManager);
    }
}