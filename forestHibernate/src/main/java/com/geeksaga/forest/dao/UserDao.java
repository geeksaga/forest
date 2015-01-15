package com.geeksaga.forest.dao;

import com.geeksaga.forest.repositories.entity.User;

public interface UserDao extends EntityDao<User>
{
    User findByName(String name);
}