package com.geeksaga.forest.repositories.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.geeksaga.forest.dao.UserDao;
import com.geeksaga.forest.repositories.UserRepository;
import com.geeksaga.forest.repositories.entity.QUser;
import com.geeksaga.forest.repositories.entity.User;

@Repository
public class UserDaoImpl extends AbstractSpringDataDao<User> implements UserDao
{
    @Autowired
    private UserRepository userRepository;

    @Override
    public User findByName(String name)
    {
        return userRepository.findOne(QUser.user.name.eq(name));
        // return userRepository.findByName(name);
    }
}
