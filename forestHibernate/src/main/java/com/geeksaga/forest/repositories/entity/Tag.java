package com.geeksaga.forest.repositories.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.geeksaga.common.annotation.PrintToString;

/**
 * @author geeksaga
 * @version 0.1
 */
@Entity
@Table(name = "pw_tags", schema = "")
public class Tag extends BaseEntity implements Serializable
{
    private static final long serialVersionUID = 1L;

    @PrintToString
    @Column(name = "target_sid", nullable = false)
    private Long targetSid;

    @PrintToString
    @Column(name = "tag_name", nullable = false, columnDefinition = "VARCHAR(64)", unique = true)
    private String tagName;

    @PrintToString
    private int tagCnt;

    @PrintToString
    @Column(name = "hit_cnt", nullable = false, columnDefinition = "INTEGER DEFAULT 1")
    private int hitCnt;

    @PrintToString
    @Column(name = "parsing", nullable = false)
    private String parsing;

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