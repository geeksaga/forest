package com.geeksaga.forest.repositories.entity;

import com.mysema.query.types.expr.BooleanExpression;

public class UserPredicates
{
    public static BooleanExpression nameLike(final String searchTerm)
    {
        return QUser.user.name.contains(searchTerm);
    }

    public static BooleanExpression findMatchUsers(final Long userManagerId)
    {
        return QUser.user.userManager.sid.eq(userManagerId);
    }
}