package com.geeksaga.forest.repositories.entity;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.geeksaga.common.annotation.PrintToString;

/**
 * @author geeksaga
 * @version 0.1
 */
@Entity
@Table(name = "pw_seeds", schema = "")
public class Seed extends BaseEntity implements Serializable
{
    private static final long serialVersionUID = 1L;
    
    @PrintToString
    @Column(name = "title", nullable = false, unique = true)
    private String title;

    @Column(name = "content", nullable = false)
    private String content;

    @Basic
    @Column(name = "userSid")
    private Long userSid;
    
    @Basic
    @Column(name = "tagName")
    private String tagName;
    
    @Basic
    @Column(name = "tagSid")
    private Long tagSid;
    
    @Basic
    @Column(name = "delYn", nullable = false, columnDefinition="VARCHAR(1) DEFAULT 'N'")
    private String delYn;
    
    @Basic
    @Column(name = "registTimestamp", nullable = false)
    private String registTimestamp;
    
    @Basic
    @Column(name = "modifyTimestamp")
    private String modifyTimestamp;

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getContent()
    {
        return content;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

    public Long getUserSid()
    {
        return userSid;
    }

    public void setUserSid(Long userSid)
    {
        this.userSid = userSid;
    }

    public String getTagName()
    {
        return tagName;
    }

    public void setTagName(String tagName)
    {
        this.tagName = tagName;
    }

    public Long getTagSid()
    {
        return tagSid;
    }

    public void setTagSid(Long tagSid)
    {
        this.tagSid = tagSid;
    }

    public String getDelYn()
    {
        return delYn;
    }

    public void setDelYn(String delYn)
    {
        this.delYn = delYn;
    }

    public String getRegistTimestamp()
    {
        return registTimestamp;
    }

    public void setRegistTimestamp(String registTimestamp)
    {
        this.registTimestamp = registTimestamp;
    }

    public String getModifyTimestamp()
    {
        return modifyTimestamp;
    }

    public void setModifyTimestamp(String modifyTimestamp)
    {
        this.modifyTimestamp = modifyTimestamp;
    }
}