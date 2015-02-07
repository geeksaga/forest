package com.geeksaga.forest.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.geeksaga.common.annotation.PrintToString;

/**
 * @author geeksaga
 * @version 0.1
 */
@Entity
@Table(name = "pw_authentication", schema = "")
public class Authentication extends BaseEntity implements Serializable
{
    private static final long serialVersionUID = 1L;

    @PrintToString
    private Long userSid;
    @PrintToString
    private Long authenticationKey;
    @PrintToString
    private String registTimestamp;

    public Authentication()
    {}

    public Authentication(Long authenticationKey)
    {
        this.authenticationKey = authenticationKey;
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