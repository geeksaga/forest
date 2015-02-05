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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.geeksaga.common.util.HtmlUtil;

@Entity
@Table(name = "pw_seeds", schema = "")
public class Seed extends BaseEntity implements Serializable
{
    private static final long serialVersionUID = 1L;

    @Column(name = "title", nullable = false, unique = true)
    private String title;

    @Column(name = "content", nullable = false)
    private String content;

    @Basic
    @Column(name = "user_sid")
    private Long userSid;

    @Basic
    @Column(name = "tag_sid")
    private Long tagSid;

    @Basic
    @Column(name = "delYn", nullable = false, columnDefinition = "VARCHAR(1) DEFAULT 'N'")
    private String delYn;

    @Basic
    @Column(name = "regist_timestamp", nullable = false)
    private String registTimestamp;

    @Basic
    @Column(name = "modify_timestamp", nullable = false)
    private String modifyTimestamp;

    @Transient
    private String tag;

    @Transient
    private List<MultipartFile> file = new ArrayList<MultipartFile>();

    // @ManyToMany(cascade = CascadeType.ALL)
    // @JoinTable(joinColumns = { @JoinColumn(name = "employeeId") }, inverseJoinColumns = {
    // @JoinColumn(table = "project", name = "projectId"), @JoinColumn(table = "localproject", name = "projectId"),
    // @JoinColumn(table = "globalproject", name = "projectId") })
    @Transient
    private List<AttachFile> files = new ArrayList<AttachFile>();

    // @OneToMany(mappedBy = "tag_sid", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "tag_sid", nullable = true, insertable = true, updatable = false)
    private List<Tag> tags = new ArrayList<Tag>();

    public List<Tag> getTags()
    {
        return tags;
    }

    public void setTags(List<Tag> tags)
    {
        this.tags = tags;
    }

    /**
     * 검색엔진에 추가할 인덱스 데이터
     * 
     * @return
     */
    @JsonIgnore
    public String getIndexingData()
    {
        StringBuilder sb = new StringBuilder();
        sb.append(getTitle());
        sb.append("\r\n");
        sb.append(getTag());
        sb.append("\r\n");
        sb.append(HtmlUtil.removeTag(getContent()));

        Iterator<MultipartFile> iterator = getFile().iterator();

        while (iterator.hasNext())
        {
            sb.append("\r\n");
            sb.append(iterator.next().getOriginalFilename());
        }

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

    public Long getTagSid()
    {
        return tagSid;
    }

    public void setTagSid(Long tagSid)
    {
        this.tagSid = tagSid;
    }

    @JsonIgnore
    @JsonProperty(value = "delYn")
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

    @JsonIgnore
    @JsonProperty(value = "tag")
    public String getTag()
    {
        return tag;
    }

    public void setTag(String tag)
    {
        this.tag = tag;
    }

    @JsonIgnore
    @JsonProperty(value = "file")
    public List<MultipartFile> getFile()
    {
        return file;
    }

    public void setFile(List<MultipartFile> file)
    {
        this.file = file;
    }

    public List<AttachFile> getFiles()
    {
        return files;
    }

    public void setFiles(List<AttachFile> files)
    {
        this.files = files;
    }

    public String toString()
    {
        return "Seed [title = " + title + ", content =" + content + "]";
    }
}