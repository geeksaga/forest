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
    ANONYMOUS(Code.ANONYMOUS);

    private Code code;
    private static final String ROLE_SUPER_USER = "ROLE_SUPER_USER";
    private static final String ROLE_ADMIN = "ROLE_ADMIN";
    private static final String ROLE_USER = "ROLE_USER";
    private static final String ROLE_ANONYMOUS = "ROLE_ANONYMOUS";

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