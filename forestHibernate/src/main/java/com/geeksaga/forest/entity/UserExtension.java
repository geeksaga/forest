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
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "pw_user_extension", schema = "")
public class UserExtension implements Serializable
{
    private static final long serialVersionUID = 1L;

    @Id
    @NotNull
    @Column(name = "user_sid", nullable = false)
    private String userSid;

    @Column(name = "image_file", length=128)
    private String imageFile;
    
    @Column(name = "icon_file", length=128)
    private String iconFile;
    
    @Column(name = "public_code", length=12)
    private String publicCode;

    @Column(name = "password_question", length = 255)
    private String passwordQuestion;

    @Column(name = "password_answer", length = 255)
    private String passwordAnswer;

    @Column(name = "email_yn")
    private boolean isSendEmail;

    @Column(name = "homepage", length = 255)
    private String homepage;

    @Column(name = "point")
    private int point;

    @Column(name = "comment", length = 2000)
    private String comment;

    @Column(name = "regist_timestamp", nullable = false)
    private String registTimestamp;

    @Column(name = "modify_timestamp", nullable = false)
    private String modifyTimestamp;

    public String getUserSid()
    {
        return userSid;
    }

    public void setUserSid(String userSid)
    {
        this.userSid = userSid;
    }

    public String getPublicCode()
    {
        return publicCode;
    }

    public void setPublicCode(String publicCode)
    {
        this.publicCode = publicCode;
    }

    public String getPasswordQuestion()
    {
        return passwordQuestion;
    }

    public void setPasswordQuestion(String passwordQuestion)
    {
        this.passwordQuestion = passwordQuestion;
    }

    public String getPasswordAnswer()
    {
        return passwordAnswer;
    }

    public void setPasswordAnswer(String passwordAnswer)
    {
        this.passwordAnswer = passwordAnswer;
    }

    public boolean isSendEmail()
    {
        return isSendEmail;
    }

    public void setSendEmail(boolean isSendEmail)
    {
        this.isSendEmail = isSendEmail;
    }

    public String getHomepage()
    {
        return homepage;
    }

    public void setHomepage(String homepage)
    {
        this.homepage = homepage;
    }

    public int getPoint()
    {
        return point;
    }

    public void setPoint(int point)
    {
        this.point = point;
    }

    public String getComment()
    {
        return comment;
    }

    public void setComment(String comment)
    {
        this.comment = comment;
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