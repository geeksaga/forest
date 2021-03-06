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

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "pw_authentication", schema = "")
public class Authentication extends BaseEntity implements Serializable
{
    private static final long serialVersionUID = 1L;

    @Column(name = "user_sid", nullable = false)
    private Long userSid;
    
    @Column(name = "authentication_key", nullable = false)
    private Long authenticationKey;
    
    @Column(name = "regist_timestamp", nullable = false)
    private String registTimestamp;

    public Authentication()
    {}

    public Authentication(Long userSid)
    {
        this.userSid = userSid;
    }

    public Long getUserSid()
    {
        return userSid;
    }

    public void setUserSid(Long userSid)
    {
        this.userSid = userSid;
    }

    public Long getAuthenticationKey()
    {
        return authenticationKey;
    }

    public void setAuthenticationKey(Long authenticationKey)
    {
        this.authenticationKey = authenticationKey;
    }

    public String getRegistTimestamp()
    {
        return registTimestamp;
    }

    public void setRegistTimestamp(String registTimestamp)
    {
        this.registTimestamp = registTimestamp;
    }
}