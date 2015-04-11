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

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "pw_primary_code", catalog = "", schema = "")
public class PrimaryCode implements Serializable
{
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private PK pk = new PK();
    
    @Size(max = 255)
    @Column(name = "primary_code_name", nullable = false, length = 255)
    private String primaryCodeName;
    
    @Column(name = "use_yn", nullable = false, columnDefinition = "boolean default true")
    private boolean use;
    
    @Embeddable
    public static class PK implements Serializable
    {
        private static final long serialVersionUID = 1L;

        @NotNull
        @Size(min = 6, max = 6)
        @Column(name = "primary_code", nullable = false, unique = true, length = 6)
        private String primaryCode;
        
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
            result = 31 * result + (primaryCode != null ? primaryCode.hashCode() : 0);
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
    
    public String getPrimaryCodeName()
    {
        return primaryCodeName;
    }

    public void setPrimaryCodeName(String primaryCodeName)
    {
        this.primaryCodeName = primaryCodeName;
    }

    public boolean isUse()
    {
        return use;
    }

    public void setUse(boolean use)
    {
        this.use = use;
    }
    
    public String toString()
    {
        return "PrimaryCode [PK.primaryCode = " + getPk().getPrimaryCode() + ", PK.locale = " + getPk().getLocale() + "]";
    }
}