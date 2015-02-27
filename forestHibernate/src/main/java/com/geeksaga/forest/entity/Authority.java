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
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "pw_authority", schema = "")
//@AssociationOverrides({ @AssociationOverride(name = "user", joinColumns = @JoinColumn(name = "user_sid")) })
public class Authority extends BaseEntity implements Serializable
{
    private static final long serialVersionUID = 1L;

    @NotNull
    @Size(min = 2, max = 64)
    @Column(name = "role", nullable = false, length = 64)
    private String role;

    @Size(min = 6, max = 12)
    @Column(name = "target_type", length = 12)
    private String targetType;

    @NotNull
    @Column(name = "regist_timestamp", nullable = false)
    private String registTimestamp;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_sid", referencedColumnName = "sid", nullable = false)
    private User user;

    public Authority()
    {}

    public Authority(Long userSid, String role)
    {
        this(0L, userSid, role, null);
    }

    public Authority(Long sid, Long userSid, String role)
    {
        this(sid, userSid, role, null);
    }

    public Authority(Long sid, Long userSid, String role, String targetType)
    {
        setSid(sid);
        setUser(new User(userSid));
        setRole(role);
        setTargetType(targetType);
    }

    public String getRole()
    {
        return role;
    }

    public void setRole(String role)
    {
        this.role = role;
    }

    public String getTargetType()
    {
        return targetType;
    }

    public void setTargetType(String targetType)
    {
        this.targetType = targetType;
    }

    public String getRegistTimestamp()
    {
        return registTimestamp;
    }

    public void setRegistTimestamp(String registTimestamp)
    {
        this.registTimestamp = registTimestamp;
    }

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }

    @Override
    public String toString()
    {
        return "Authority [sid=" + getSid() + ", user=" + getUser() + ", role=" + getRole() + ", targetType=" + getTargetType() + "]";
    }
}