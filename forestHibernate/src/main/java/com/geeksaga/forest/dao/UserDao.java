package com.geeksaga.forest.dao;

import com.geeksaga.forest.repositories.entity.User;

/**
 * @author geeksaga
 * @version 0.1
 */
public interface UserDao extends EntityDao<User>
{
    User findByName(String name);
}