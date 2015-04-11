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
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.geeksaga.common.util.HtmlUtil;

@Entity
@Table(name = "pw_seeds", catalog = "", schema = "", uniqueConstraints = { @UniqueConstraint(columnNames = "title") })
public class Seed extends BaseEntity implements Serializable
{
    private static final long serialVersionUID = 1L;

    @NotNull
    @Column(name = "title", nullable = false, unique = true)
    private String title;

    @NotNull
    @Column(name = "content", nullable = false, updatable = true)
    private String content;

    @NotNull
    @Column(name = "user_sid", nullable = false)
    private Long userSid;
    
    @NotNull
    @Column(name = "view_count", nullable = false)
    private int viewCount;
    
    @NotNull
    @Column(name = "comment_count", nullable = false)
    private int commentCount;
    
    @NotNull
    @Column(name = "good_count", nullable = false)
    private int goodCount;

    @Column(name = "del_yn", updatable = true, columnDefinition = "boolean default false")
    private boolean del;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "regist_timestamp", nullable = false)
    private Date registTimestamp;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modify_timestamp", nullable = false)
    private Date modifyTimestamp;

    @Transient
    private String tag;

    @Transient
    private List<MultipartFile> file = new ArrayList<MultipartFile>();

    // @ElementCollection
    // @CollectionTable
    // @OrderColumn
    // @ManyToMany(cascade = CascadeType.ALL)
    // @JoinTable(joinColumns = { @JoinColumn(name = "employeeId") }, inverseJoinColumns = {
    // @JoinColumn(table = "project", name = "projectId"), @JoinColumn(table = "localproject", name = "projectId"),
    // @JoinColumn(table = "globalproject", name = "projectId") })
    @Transient
    private List<AttachFile> files = new ArrayList<AttachFile>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.seed", cascade = CascadeType.ALL)
    private Set<TagMap> tagSet = new HashSet<TagMap>(0);

    public Seed()
    {}

    public Seed(Long sid, String title, String content, Long userSid)
    {
        setSid(sid);
        setTitle(title);
        setContent(content);
        setUserSid(userSid);
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

    @PrePersist
    public void prePersist()
    {
        setViewCount(0);
        setCommentCount(0);
        setGoodCount(0);
        setRegistTimestamp(new Date());
        setModifyTimestamp(new Date());
    }
    
    @PreUpdate
    public void preUpdate()
    {
        setModifyTimestamp(new Date());
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

    @Transient
    public Long getUserSid()
    {
        return userSid;
    }

    @Transient
    public void setUserSid(Long userSid)
    {
        this.userSid = userSid;
    }

    public int getViewCount()
    {
        return viewCount;
    }

    public void setViewCount(int viewCount)
    {
        this.viewCount = viewCount;
    }

    public int getCommentCount()
    {
        return commentCount;
    }

    public void setCommentCount(int commentCount)
    {
        this.commentCount = commentCount;
    }

    public int getGoodCount()
    {
        return goodCount;
    }

    public void setGoodCount(int goodCount)
    {
        this.goodCount = goodCount;
    }
    
    @JsonIgnore
    @JsonProperty(value = "del")
    public boolean isDel()
    {
        return del;
    }

    public void setDel(boolean del)
    {
        this.del = del;
    }

    public Date getRegistTimestamp()
    {
        return registTimestamp;
    }

    public void setRegistTimestamp(Date registTimestamp)
    {
        this.registTimestamp = registTimestamp;
    }

    public Date getModifyTimestamp()
    {
        return modifyTimestamp;
    }

    public void setModifyTimestamp(Date modifyTimestamp)
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
    
    public Set<TagMap> getTagSet()
    {
        return tagSet;
    }

    public void setTagSet(Set<TagMap> tagSet)
    {
        this.tagSet = tagSet;
    }

    public String toString()
    {
        return "Seed [sid = " + getSid() + ", title = " + getTitle() + ", content =" + getContent() + "]";
    }
}