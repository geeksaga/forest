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
package com.geeksaga.forest.repositories.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "pw_tags", schema = "")
public class Tag extends BaseEntity implements Serializable
{
    private static final long serialVersionUID = 1L;

    @Column(name = "tag_name", nullable = false, columnDefinition = "VARCHAR(64)", unique = true)
    private String tagName;

    @Column(name = "hit_cnt", nullable = false, columnDefinition = "INTEGER DEFAULT 1")
    private int hitCnt;

    @Column(name = "parsing", nullable = false)
    private String parsing;
    
    @Transient
    private Long targetSid;
    
    @Transient
    private int tagCnt;

    public Tag()
    {}

    public Tag(String tagName, String parsing)
    {
        this.tagName = tagName;
        this.parsing = parsing;
    }

    public Long getTargetSid()
    {
        return targetSid;
    }

    public void setTargetSid(Long targetSid)
    {
        this.targetSid = targetSid;
    }

    public String getTagName()
    {
        return tagName;
    }

    public void setTagName(String tagName)
    {
        this.tagName = tagName;
    }

    public int getTagCnt()
    {
        return tagCnt;
    }

    public void setTagCnt(int tagCnt)
    {
        this.tagCnt = tagCnt;
    }

    public int getHitCnt()
    {
        return hitCnt;
    }

    public void setHitCnt(int hitCnt)
    {
        this.hitCnt = hitCnt;
    }

    public String getParsing()
    {
        return parsing;
    }

    public void setParsing(String parsing)
    {
        this.parsing = parsing;
    }

    public String toString()
    {
        return "Tag [tagName = " + tagName + ", parsing = " + parsing + "]";
    }
}