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
package com.geeksaga.forest.entity;

import java.io.Serializable;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "pw_tag_map", catalog = "", schema = "")
@AssociationOverrides({ @AssociationOverride(name = "pk.seed", joinColumns = @JoinColumn(name = "target_sid")),
        @AssociationOverride(name = "pk.tag", joinColumns = @JoinColumn(name = "tag_sid")) })
public class TagMap implements Serializable
{
    private static final long serialVersionUID = 1L;

    public static enum CNT_TYPE
    {
        TAG_PLUS_CNT, TAG_MINUS_CNT
    };

    @EmbeddedId
    private PK pk = new PK();

    @Column(name = "regist_timestamp", nullable = false, length = 14)
    private String registTimestamp;

    // @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    // // @MapsId("tag_sid")
    // // (mappedBy = "sid")
    // @JoinColumn(name = "tag_sid", referencedColumnName = "sid", insertable = false, updatable = false)
    // // , insertable = false, updatable = false)
    // private Tag tag;
    //
    // public Tag getTag()
    // {
    // return tag;
    // }
    //
    // public void setTag(Tag tag)
    // {
    // this.tag = tag;
    // }

    // @ManyToOne(optional = false)
    // @JoinColumn(name = "tag_sid", referencedColumnName = "sid", insertable = false, updatable = false)
    // // // @JoinColumn(name = "sid", referencedColumnName = "sid", insertable = false, updatable = false)
    // // // @JoinTable(name="permission", joinColumns= {
    // // // @JoinColumn(name="col1", referencedColumnName="col1", nullable=false, updatable=false),
    // // // @JoinColumn(name="col2", referencedColumnName="col2", ...),
    // // // @JoinColumn(name="col3", referencedColumnName="col3", ...)
    // // // }, inverseJoinColumns= { @JoinColumn(name="num") })
    // private Tag tag;
    //
    // public Tag getTag()
    // {
    // return tag;
    // }
    //
    // public void setTag(Tag tag)
    // {
    // this.tag = tag;
    // }
    // @JoinTable(name = "student_class", catalog = "tutorials", joinColumns = { @JoinColumn(name = "ID_STUDENT", nullable = false,
    // updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "CLASS_ID", nullable = false, updatable = false) })

    public TagMap()
    {}
    
    public TagMap(Seed seed, Tag tag)
    {
        this.pk.seed = seed;
        this.pk.tag = tag;
    }

    public PK getPk()
    {
        return pk;
    }

    public void setPk(PK pk)
    {
        this.pk = pk;
    }

    @Transient
    public Seed getSeed()
    {
        return getPk().getSeed();
    }

    public void setSeed(Seed seed)
    {
        getPk().setSeed(seed);
    }

    @Transient
    public Tag getTag()
    {
        return getPk().getTag();
    }

    public void setTag(Tag tag)
    {
        getPk().setTag(tag);
    }

    // public Long getTargetSid()
    // {
    // return targetSid;
    // }
    //
    // public void setTargetSid(Long targetSid)
    // {
    // this.targetSid = targetSid;
    // }

    public String getRegistTimestamp()
    {
        return registTimestamp;
    }

    public void setRegistTimestamp(String registTimestamp)
    {
        this.registTimestamp = registTimestamp;
    }

    // @Embeddable
    // public static class PK implements Serializable
    // {
    // private static final long serialVersionUID = 1L;
    //
    // public PK()
    // {}
    //
    // public PK(Long targetSid)
    // {
    // this.targetSid = targetSid;
    // }
    //
    // @Column(name = "target_sid", nullable = false)
    // private Long targetSid;
    //
    // @Column(name = "tag_sid", nullable = false)
    // private Long tagSid;
    //
    // public Long getTargetSid()
    // {
    // return targetSid;
    // }
    //
    // public void setTargetSid(Long targetSid)
    // {
    // this.targetSid = targetSid;
    // }
    //
    // public Long getTagSid()
    // {
    // return tagSid;
    // }
    //
    // public void setTagSid(Long tagSid)
    // {
    // this.tagSid = tagSid;
    // }
    //
    // @Override
    // public boolean equals(Object o)
    // {
    // if (o == this)
    // {
    // return true;
    // }
    //
    // if (!(o instanceof PK))
    // {
    // return false;
    // }
    //
    // PK other = (PK) o;
    //
    // return this.targetSid.equals(other.targetSid) && this.tagSid.equals(other.tagSid);
    // }
    //
    // @Override
    // public int hashCode()
    // {
    // return this.targetSid.hashCode() ^ this.tagSid.hashCode();
    // }
    // }

    @Embeddable
    public static class PK implements Serializable
    {
        private static final long serialVersionUID = 1L;

        @ManyToOne
        private Seed seed;

        @ManyToOne
        private Tag tag;

        public Seed getSeed()
        {
            return seed;
        }

        public void setSeed(Seed seed)
        {
            this.seed = seed;
        }

        public Tag getTag()
        {
            return tag;
        }

        public void setTag(Tag tag)
        {
            this.tag = tag;
        }

        @Override
        public boolean equals(Object o)
        {
            if (o == this)
            {
                return true;
            }

            if (o == null)
            {
                return false;
            }

            if (!(o instanceof PK))
            {
                return false;
            }
            PK other = (PK) o;
            if (tag == null)
            {
                if (other.tag != null)
                {
                    return false;
                }
            }
            else if (!tag.equals(other.tag))
            {
                return false;
            }

            if (seed == null)
            {
                if (other.seed != null)
                {
                    return false;
                }
            }
            else if (!seed.equals(other.seed))
            {
                return false;
            }

            return true;
        }

        @Override
        public int hashCode()
        {
            int result = (seed != null ? seed.hashCode() : 0);
            result = 17 * result + (tag != null ? tag.hashCode() : 0);

            return result;
        }
    }

    public String toString()
    {
        return "TagMap [PK.seed = " + pk.seed + ", PK.tag = " + pk.tag + "]";
    }
}