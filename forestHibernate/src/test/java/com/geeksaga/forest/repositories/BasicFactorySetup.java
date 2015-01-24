package com.geeksaga.forest.repositories;

import static org.junit.Assert.assertEquals;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;

import com.geeksaga.common.util.KeyGenerator;
import com.geeksaga.forest.repositories.entity.User;

@Ignore
public class BasicFactorySetup
{
    private static final EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpa.sample.plain");

    private SimpleUserRepository userRepository;
    // @Autowired
    // private UserManagerRepository userManagerRepository;
    private EntityManager em;

    private User user;

    @Before
    public void setUp()
    {
        em = factory.createEntityManager();

        userRepository = new JpaRepositoryFactory(em).getRepository(SimpleUserRepository.class);

        // userManagerRepository = new JpaRepositoryFactory(em).getRepository(UserManagerRepository.class);

        em.getTransaction().begin();

        user = new User();
        user.setSid(KeyGenerator.generateKeyToLong());
        user.setName("geeksaga");
        user.setEmail("geeksaga@geeksaga.com");

//        UserManager manager = new UserManager();
//        manager.setId(2);
//        manager.setName("new_manager");
//
//        List<User> users = Lists.newArrayList();
//        manager.setUsers(users);
//        users.add(user);
//
//        user.setUserManager(manager);
        // user.setUsername("username");
        // user.setFirstname("firstname");
        // user.setLastname("lastname");

        user = userRepository.save(user);

        // userManagerRepository.save(manager);
    }

    @After
    public void tearDown()
    {
        em.getTransaction().rollback();
    }

    @Test
    public void executingFinders()
    {
        assertEquals(user, userRepository.findByName("geeksaga"));
        // assertEquals(user, userRepository.findByName("geeksaga").get(0));
        // assertEquals(user, userRepository.findByTheName("geeksaga"));

        // assertEquals(user, userRepository.findByTheUsersName("username"));
        // assertEquals(user, userRepository.findByLastname("lastname").get(0));
        // assertEquals(user, userRepository.findByFirstname("firstname").get(0));
    }
}