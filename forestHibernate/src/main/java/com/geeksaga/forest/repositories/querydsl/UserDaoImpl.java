package com.geeksaga.forest.repositories.querydsl;

import com.geeksaga.forest.entity.QUser;
import com.geeksaga.forest.entity.User;

// @Repository
public abstract class UserDaoImpl extends AbstractQueryDslDao<User, QUser>
{
    public UserDaoImpl()
    {
        super(User.class, QUser.user);
    }

    public User findByName(String name)
    {
        return getSelectQuery().where(q.name.eq(name)).uniqueResult(q);
    }
}