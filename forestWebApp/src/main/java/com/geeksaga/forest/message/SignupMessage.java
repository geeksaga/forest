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
package com.geeksaga.forest.message;

import java.util.ArrayList;
import java.util.List;

import com.geeksaga.forest.common.util.EmailUtil;
import com.geeksaga.forest.entity.User;

public abstract class SignupMessage implements Message
{
    public static final String SUBJECT_PREFIX = "[Forest]";
    public static final String SENDER_EMAIL = "sns@geeksaga.com";

    protected List<User> userList;

    public SignupMessage()
    {}

    public SignupMessage(List<User> userList)
    {
        this.userList = userList;
    }

    public SignupMessage(User user)
    {
        this.userList = new ArrayList<User>();
        this.userList.add(user);
    }

    public String getFrom()
    {
        return SENDER_EMAIL;
    }

    public String[] getReceivers()
    {
        return EmailUtil.extractMailAllowedEmailFrom(userList);
    }

    public boolean isHTML()
    {
        return true;
    }

    public abstract String getTitle();

    public abstract String getMessage();
}