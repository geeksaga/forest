package com.geeksaga.forest.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author geeksaga
 * @version 0.1
 */
@Entity
@Table(name = "pw_files", schema = "")
public class AttachFile extends BaseEntity implements Serializable
{
    private static final long serialVersionUID = 1L;

    @Column(name = "target_id", nullable = false)
    private String targetId;

    @Column(name = "target_sid", nullable = false)
    private Long targetSid;
    
    @Column(name = "attach_order")
    private String attachOrder;
    
    @Column(name = "original_name")
    private String originalName;
    
    @Column(name = "file_name")
    private String fileName;
    
    @Column(name = "file_extension")
    private String fileExtension;
    
    @Column(name = "file_size")
    private long fileSize;
    
    @Column(name = "file_desc")
    private String fileDesc;
    
    @Column(name = "attach_type")
    private String attachType;
    
    @Column(name = "download_count")
    private int downloadCount;

    public AttachFile()
    {}
    
    public String getTargetId()
    {
        return targetId;
    }

    public void setTargetId(String targetId)
    {
        this.targetId = targetId;
    }

    public Long getTargetSid()
    {
        return targetSid;
    }

    public void setTargetSid(Long targetSid)
    {
        this.targetSid = targetSid;
    }

    public String getAttachOrder()
    {
        return attachOrder;
    }

    public void setAttachOrder(String attachOrder)
    {
        this.attachOrder = attachOrder;
    }

    public String getOriginalName()
    {
        return originalName;
    }

    public void setOriginalName(String originalName)
    {
        this.originalName = originalName;
    }

    public String getFileName()
    {
        return fileName;
    }

    public void setFileName(String fileName)
    {
        this.fileName = fileName;
    }

    public String getFileExtension()
    {
        return fileExtension;
    }

    public void setFileExtension(String fileExtension)
    {
        this.fileExtension = fileExtension;
    }

    public long getFileSize()
    {
        return fileSize;
    }

    public void setFileSize(long fileSize)
    {
        this.fileSize = fileSize;
    }

    public String getFileDesc()
    {
        return fileDesc;
    }

    public void setFileDesc(String fileDesc)
    {
        this.fileDesc = fileDesc;
    }

    public String getAttachType()
    {
        return attachType;
    }

    public void setAttachType(String attachType)
    {
        this.attachType = attachType;
    }

    public int getDownloadCount()
    {
        return downloadCount;
    }

    public void setDownloadCount(int downloadCount)
    {
        this.downloadCount = downloadCount;
    }

    public String toString()
    {
        return "AttachFile [originalName = " + originalName + ", fileName = " + fileName + "]";
    }
}