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

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.geeksaga.common.annotation.PrintToString;
import com.geeksaga.common.util.HtmlUtil;

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
    @Column(name = "delYn", nullable = false, columnDefinition = "VARCHAR(1) DEFAULT 'N'")
    private String delYn;

    @Basic
    @Column(name = "registTimestamp", nullable = false)
    private String registTimestamp;

    @Basic
    @Column(name = "modifyTimestamp", nullable = false)
    private String modifyTimestamp;

    public String getIndexingData()
    {
        StringBuilder sb = new StringBuilder();
        sb.append(getTitle());
        sb.append("\r\n");
        sb.append(getTagName());
        sb.append("\r\n");
        sb.append(HtmlUtil.removeTag(getContent()));

        // Iterator<AttatchFile> iterator = getFileList().iterator();
        //
        // while(iterator.hasNext()) {
        // sb.append("\r\n");
        // sb.append(iterator.next().getOriginalName());
        // }

        return sb.toString();
    }

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

    public String toString()
    {
        return "Seed [title = " + title + ", content =" + content + "]";
    }
}