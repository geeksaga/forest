package com.geeksaga.forest.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.geeksaga.common.annotation.PrintToString;

/**
 * @author geeksaga
 * @since 1.0
 * @version 0.1
 */
@Entity
@Table(name = "pw_user_trace", schema = "")
public class UserTrace extends BaseEntity implements Serializable
{
    private static final long serialVersionUID = 1L;

    @PrintToString
    private String userSid;
    @PrintToString
    private String actionType;
    @PrintToString
    private String actionTarget;
    @PrintToString
    private String actionId;
    @PrintToString
    private String userIp;
    @PrintToString
    private String registTimestamp;

    public String getUserSid()
    {
        return userSid;
    }

    public void setUserSid(String userSid)
    {
        this.userSid = userSid;
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