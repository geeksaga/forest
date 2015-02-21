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

import com.geeksaga.forest.entity.User;

public class AuthenticationMailMessage extends SignupMessage
{
    private Long authenticationKey;

    public AuthenticationMailMessage()
    {
    }

    public AuthenticationMailMessage(User user, Long authenticationKey)
    {
        super(user);

        this.authenticationKey = authenticationKey;
    }

    @Override
    public String getTitle()
    {
        return SUBJECT_PREFIX + " " + " 가입 확인 메일 입니다.";
    }

    @Override
    public String getMessage()
    {
        return "<a href='http://www.geeksaga.com/authentication?key=" + authenticationKey + "'>http://www.geeksaga.com/authentication?key="
                + authenticationKey + "</a>";
    }
}