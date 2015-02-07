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
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "pw_tags", catalog = "", schema = "", uniqueConstraints = { @UniqueConstraint(columnNames = "tag_name") })
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

    // mappedBy 는 대상에 포함된 변수명과 일치 해야 한다.
    // @ManyToOne
    // @OneToMany(fetch = FetchType.LAZY, mappedBy = "tag", cascade = CascadeType.ALL)
    // // @MapsId("tag_sid")
    // // @MapsId("tagmap_tag_sid")
    // // @JoinColumn(name = "pk_tag_sid", insertable = false, updatable = false)
    // // // // @OneToMany(mappedBy = "tag", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    // // // // @JoinColumn(name = "tagmap_pk", referencedColumnName="sid", insertable=false, updatable=false)
    // // // // @PrimaryKeyJoinColumn
    // private List<TagMap> tagMaps = new ArrayList<TagMap>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.tag")
    private Set<TagMap> tagSet = new HashSet<TagMap>(0);

    public Set<TagMap> getTagSet()
    {
        return tagSet;
    }

    public void setTagSet(Set<TagMap> tagSet)
    {
        this.tagSet = tagSet;
    }

    // private Seed seed;
    //
    // @ManyToOne
    // @JoinColumn(name="sid", insertable=false, updatable=false)
    // public Seed getSeed()
    // {
    // return seed;
    // }

    // public List<TagMap> getTagMaps()
    // {
    // return tagMaps;
    // }
    //
    // public void setTagMaps(List<TagMap> tagMaps)
    // {
    // this.tagMaps = tagMaps;
    // }

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
        return "Tag [sid = " + sid + ", tagName = " + tagName + ", parsing = " + parsing + ", hitCnt = " + hitCnt + "]";
    }
}