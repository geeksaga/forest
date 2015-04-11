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
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
// @Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "pw_authority", schema = "")
// @AssociationOverrides({ @AssociationOverride(name = "user", joinColumns = @JoinColumn(name = "user_sid")) })
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
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "regist_timestamp", nullable = false)
    private Date registTimestamp;

    // @NotNull
    // @Column(name = "user_sid", nullable = false)
    // private Long userSid;

    @ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.REMOVE })
    @JoinColumn(name = "user_sid", referencedColumnName = "sid", nullable = false)
    // @Fetch(FetchMode.SELECT)
    // @Transient
    private User user;

    public Authority()
    {}

    public Authority(Long userSid, String role)
    {
        this(0L, userSid, role);
    }

    public Authority(Long sid, Long userSid, String role)
    {
        this(sid, userSid, role, null, new Date());
    }

    public Authority(Long sid, Long userSid, String role, String targetType, Date registTimestamp)
    {
        setSid(sid);
        setUser(new User(userSid));
        setRole(role);
        setTargetType(targetType);
        setRegistTimestamp(registTimestamp);
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

    public Date getRegistTimestamp()
    {
        return registTimestamp;
    }

    public void setRegistTimestamp(Date registTimestamp)
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