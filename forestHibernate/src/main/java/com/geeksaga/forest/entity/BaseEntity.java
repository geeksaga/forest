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

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

@MappedSuperclass
public class BaseEntity
{
    @Id
    @NotNull
    @Column(name = "sid", nullable = false, updatable = false)
    protected Long sid;

    public Long getSid()
    {
        return sid;
    }

    public void setSid(Long sid)
    {
        this.sid = sid;
    }
}