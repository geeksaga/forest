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

import com.mysema.query.types.expr.BooleanExpression;

public class AuthorityPredicates
{
    public static BooleanExpression userSid(final Long userSid)
    {
        return QAuthority.authority.user.sid.eq(userSid);
    }
}