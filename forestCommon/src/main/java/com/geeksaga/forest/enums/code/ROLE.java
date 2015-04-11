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
package com.geeksaga.forest.enums.code;

public enum ROLE
{
    SUPER_USER(Code.SUPER_USER), //
    ADMIN(Code.ADMIN), //
    USER(Code.USER), //
    USER_VERIFIED(Code.USER_VERIFIED), //
    ANONYMOUS(Code.ANONYMOUS);

    private Code code;
    public static final String ROLE_SUPER_USER = "ROLE_SUPER_USER";
    public static final String ROLE_ADMIN = "ROLE_ADMIN";
    public static final String ROLE_USER = "ROLE_USER";
    public static final String ROLE_USER_VERIFIED = "ROLE_USER_VERIFIED";
    public static final String ROLE_ANONYMOUS = "ROLE_ANONYMOUS";

    ROLE(Code code)
    {
        this.code = code;
    }

    private enum Code
    {
        SUPER_USER
        {
            String getCode()
            {
                return ROLE_SUPER_USER;
            }
        },

        ADMIN
        {
            String getCode()
            {
                return ROLE_ADMIN;
            }
        },

        USER
        {
            String getCode()
            {
                return ROLE_USER;
            }
        },
        
        USER_VERIFIED
        {
            String getCode()
            {
                return ROLE_USER_VERIFIED;
            }
        },

        ANONYMOUS
        {
            String getCode()
            {
                return ROLE_ANONYMOUS;
            }
        };

        abstract String getCode();
    }

    public String getCode()
    {
        return code.getCode();
    }

    public boolean equals(String value)
    {
        return code.getCode().equals(value);
    }
}