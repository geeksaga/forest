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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "pw_authority", schema = "")
public class Authority extends BaseEntity implements Serializable
{
    private static final long serialVersionUID = 1L;

    @NotNull
    @Column(name = "user_sid", nullable = false)
    private Long userSid;

    @NotNull
    @Size(min = 2, max = 64)
    @Column(name = "authority", nullable = false, length = 64)
    private String authority;

    @Size(min = 6, max = 12)
    @Column(name = "target_type", length = 12)
    private String targetType;

    @NotNull
    @Column(name = "regist_timestamp", nullable = false)
    private String registTimestamp;

    public Authority()
    {}

    public Authority(Long userSid, String authority)
    {
        this(0L, userSid, authority, null);
    }
    
    public Authority(Long sid, Long userSid, String authority)
    {
        this(sid, userSid, authority, null);
    }

    public Authority(Long sid, Long userSid, String authority, String targetType)
    {
        setSid(sid);
        setUserSid(userSid);
        setAuthority(authority);
        setTargetType(targetType);
    }

    public Long getUserSid()
    {
        return userSid;
    }

    public void setUserSid(Long userSid)
    {
        this.userSid = userSid;
    }

    public String getAuthority()
    {
        return authority;
    }

    public void setAuthority(String authority)
    {
        this.authority = authority;
    }

    public String getTargetType()
    {
        return targetType;
    }

    public void setTargetType(String targetType)
    {
        this.targetType = targetType;
    }
}