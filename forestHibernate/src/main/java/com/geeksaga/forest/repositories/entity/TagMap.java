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
// http://www.javabeat.net/embeddable-embedded-embeddedid-jpa-annotations/
package com.geeksaga.forest.repositories.entity;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "pw_tag_map", schema = "")
public class TagMap implements Serializable
{
    private static final long serialVersionUID = 1L;

    public static enum CNT_TYPE
    {
        TAG_PLUS_CNT, TAG_MINUS_CNT
    };

    @EmbeddedId
    private PK pk = new PK();

    @Basic
    @Column(name = "regist_timestamp", nullable = false)
    private String registTimestamp;

    public TagMap()
    {}

    public TagMap(Long targetSid)
    {
        this.pk = new PK(targetSid);
    }

    public PK getPk()
    {
        return pk;
    }

    public void setPk(PK pk)
    {
        this.pk = pk;
    }

    public String getRegistTimestamp()
    {
        return registTimestamp;
    }

    public void setRegistTimestamp(String registTimestamp)
    {
        this.registTimestamp = registTimestamp;
    }

    @Embeddable
    public static class PK implements Serializable
    {
        private static final long serialVersionUID = 1L;

        public PK()
        {}

        public PK(Long targetSid)
        {
            this.targetSid = targetSid;
        }

        @Column(name = "target_sid", nullable = false)
        private Long targetSid;

        @Column(name = "tag_sid", nullable = false)
        private Long tagSid;

        public Long getTargetSid()
        {
            return targetSid;
        }

        public void setTargetSid(Long targetSid)
        {
            this.targetSid = targetSid;
        }

        public Long getTagSid()
        {
            return tagSid;
        }

        public void setTagSid(Long tagSid)
        {
            this.tagSid = tagSid;
        }

        @Override
        public boolean equals(Object o)
        {
            if (o == this)
            {
                return true;
            }

            if (!(o instanceof PK))
            {
                return false;
            }

            PK other = (PK) o;

            return this.targetSid.equals(other.targetSid) && this.tagSid.equals(other.tagSid);
        }

        @Override
        public int hashCode()
        {
            return this.targetSid.hashCode() ^ this.tagSid.hashCode();
        }
    }
}