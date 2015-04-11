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

@Entity
@Table(name = "pw_user_trace", schema = "")
public class UserTrace extends BaseEntity implements Serializable
{
    private static final long serialVersionUID = 1L;

    @NotNull
    @Column(name = "user_sid", nullable = false)
    private Long userSid;
    @NotNull
    @Column(name = "action_type", nullable = false, length = 12)
    private String actionType;
    @NotNull
    @Column(name = "action_target", nullable = false, length = 32)
    private String actionTarget;
    @NotNull
    @Column(name = "action_id", nullable = false, length = 12)
    private String actionId;
    @NotNull
    @Column(name = "user_ip", nullable = false, length = 32)
    private String userIp;

    @Column(name = "regist_timestamp", nullable = false, length = 14)
    private String registTimestamp;

    public void setUserSid(Long userSid)
    {
        this.userSid = userSid;
    }

    public Long getUserSid()
    {
        return userSid;
    }

    public String getActionType()
    {
        return actionType;
    }

    public void setActionType(String actionType)
    {
        this.actionType = actionType;
    }

    public String getActionTarget()
    {
        return actionTarget;
    }

    public void setActionTarget(String actionTarget)
    {
        this.actionTarget = actionTarget;
    }

    public String getActionId()
    {
        return actionId;
    }

    public void setActionId(String actionId)
    {
        this.actionId = actionId;
    }

    public String getUserIp()
    {
        return userIp;
    }

    public void setUserIp(String userIp)
    {
        this.userIp = userIp;
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