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

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "pw_secondary_code", catalog = "", schema = "")
@AssociationOverrides({ @AssociationOverride(name = "pk.primaryCode", joinColumns = @JoinColumn(name = "primary_code")) })
public class SecondaryCode implements Serializable
{
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private PK pk = new PK();

    @NotNull
    @Size(max = 12)
    @Column(name = "code", nullable = false, length = 12)
    private String code;

    @Size(max = 12)
    @Column(name = "code_hieararchy", length = 12)
    private String codeHieararchy;

    @Size(max = 255)
    @Column(name = "secondary_code_name", nullable = false, length = 255)
    private String secondaryCodeName;

    @Size(max = 20)
    @Column(name = "secondary_code_value", nullable = false, length = 20)
    private String secondaryCodeValue;

    @Size(max = 1000)
    @Column(name = "secondary_code_description", length = 1000)
    private String secondaryCodeDescription;

    @Column(name = "use_yn", nullable = false, columnDefinition = "boolean default true")
    private boolean use;

    @Transient
    private boolean leaf;

    @Embeddable
    public static class PK implements Serializable
    {
        private static final long serialVersionUID = 1L;

        @NotNull
        @Size(min = 6, max = 6)
        @Column(name = "primary_code", nullable = false, unique = true, length = 6)
        private String primaryCode;

        @NotNull
        @Size(min = 6, max = 6)
        @Column(name = "secondary_code", nullable = false, unique = true, length = 6)
        private String secondaryCode;

        @NotNull
        @Size(max = 2)
        @Column(name = "locale", nullable = false, length = 2)
        private String locale;

        public String getPrimaryCode()
        {
            return primaryCode;
        }

        public void setPrimaryCode(String primaryCode)
        {
            this.primaryCode = primaryCode;
        }

        public String getSecondaryCode()
        {
            return secondaryCode;
        }

        public void setSecondaryCode(String secondaryCode)
        {
            this.secondaryCode = secondaryCode;
        }

        public String getLocale()
        {
            return locale;
        }

        public void setLocale(String locale)
        {
            this.locale = locale;
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

            if (primaryCode == null)
            {
                if (other.primaryCode != null)
                {
                    return false;
                }
            }
            else if (!primaryCode.equals(other.primaryCode))
            {
                return false;
            }

            if (secondaryCode == null)
            {
                if (other.secondaryCode != null)
                {
                    return false;
                }
            }
            else if (!secondaryCode.equals(other.secondaryCode))
            {
                return false;
            }

            if (locale == null)
            {
                if (other.locale != null)
                {
                    return false;
                }
            }
            else if (!locale.equals(other.locale))
            {
                return false;
            }

            return true;
        }

        @Override
        public int hashCode()
        {
            int result = 17;
            result = 31 * (primaryCode != null ? primaryCode.hashCode() : 0);
            result = 31 * result + (secondaryCode != null ? secondaryCode.hashCode() : 0);
            result = 31 * result + (locale != null ? locale.hashCode() : 0);

            return result;
        }
    }

    public PK getPk()
    {
        return pk;
    }

    public void setPk(PK pk)
    {
        this.pk = pk;
    }

    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

    public String getCodeHieararchy()
    {
        return codeHieararchy;
    }

    public void setCodeHieararchy(String codeHieararchy)
    {
        this.codeHieararchy = codeHieararchy;
    }

    public String getSecondaryCodeName()
    {
        return secondaryCodeName;
    }

    public void setSecondaryCodeName(String secondaryCodeName)
    {
        this.secondaryCodeName = secondaryCodeName;
    }

    public String getSecondaryCodeValue()
    {
        return secondaryCodeValue;
    }

    public void setSecondaryCodeValue(String secondaryCodeValue)
    {
        this.secondaryCodeValue = secondaryCodeValue;
    }

    public String getSecondaryCodeDescription()
    {
        return secondaryCodeDescription;
    }

    public void setSecondaryCodeDescription(String secondaryCodeDescription)
    {
        this.secondaryCodeDescription = secondaryCodeDescription;
    }

    @JsonIgnore
    @JsonProperty(value = "use")
    public boolean isUse()
    {
        return use;
    }

    public void setUse(boolean use)
    {
        this.use = use;
    }

    public boolean isLeaf()
    {
        return leaf;
    }

    public void setLeaf(boolean leaf)
    {
        this.leaf = leaf;
    }

    public String toString()
    {
        return "SecondaryCode [PK.primaryCode = " + getPk().getPrimaryCode() + ", PK.secondaryCode = " + getPk().getSecondaryCode()
                + ", PK.locale = " + getPk().getLocale() + "]";
    }
}