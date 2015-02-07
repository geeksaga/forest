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

import com.geeksaga.forest.entity.QUserManager;
import com.mysema.query.types.expr.BooleanExpression;

public class UserManagerPredicates
{
    public static BooleanExpression hasUsers()
    {
        return QUserManager.userManager.users.isNotEmpty();
    }
}