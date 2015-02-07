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
@Table(name = "pw_authority", schema = "")
public class Authority extends BaseEntity implements Serializable
{
    private static final long serialVersionUID = 3751748648764883216L;

    @PrintToString
    private String authority;
    @PrintToString
    private String targetType;
    @PrintToString
    private Long targetSid;

    public Authority()
    {}

    public Authority(String authority, String targetType)
    {
        this.authority = authority;
        this.targetType = targetType;
    }

    public Authority(Long sid, String authority, String targetType, Long targetSid)
    {
        this.sid = sid;
        this.authority = authority;
        this.targetType = targetType;
        this.targetSid = targetSid;
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

    public Long getTargetSid()
    {
        return targetSid;
    }

    public void setTargetSid(Long targetSid)
    {
        this.targetSid = targetSid;
    }
}