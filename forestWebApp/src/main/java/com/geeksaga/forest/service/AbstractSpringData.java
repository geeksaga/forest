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
package com.geeksaga.forest.service;

import org.springframework.data.jpa.repository.support.QueryDslRepositorySupport;

import com.geeksaga.forest.entity.BaseEntity;

public abstract class AbstractSpringData<T extends BaseEntity> extends QueryDslRepositorySupport
{
    public AbstractSpringData(Class<?> domainClass)
    {
        super(domainClass);
    }
}