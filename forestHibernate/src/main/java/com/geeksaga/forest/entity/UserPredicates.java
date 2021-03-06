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
package com.geeksaga.forest.entity;

import com.geeksaga.forest.entity.QUser;
import com.mysema.query.types.expr.BooleanExpression;

public class UserPredicates
{
    public static BooleanExpression firstNameLike(final String firstName)
    {
        return QUser.user.firstName.contains(firstName);
    }

    public static BooleanExpression findMatchUsers(final Long userSid)
    {
        return QUser.user.sid.eq(userSid);
    }
}