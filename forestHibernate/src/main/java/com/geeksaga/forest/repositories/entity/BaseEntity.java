package com.geeksaga.forest.repositories.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import com.geeksaga.common.annotation.PrintToString;

/**
 * @author geeksaga
 * @version 0.1
 */
@MappedSuperclass
public class BaseEntity
{
    @PrintToString
    @Id
    @Column(name = "sid")
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