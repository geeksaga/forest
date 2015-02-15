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

public enum AUTHORITY_CODE
{
    ALL_USER(Code.A00701), //
    NON_MEMBER(Code.A00702), //
    MEMBER(Code.A00703), //
    EXCELLENT_MEMBER(Code.A00704), //
    SPECIAL_MEMBER(Code.A00705), //
    ADMIN_USER(Code.A00706), //
    PRIMARYCODE(Code.A007);

    private Code code;
    private static final String PRIMARY_CODE = "A007";
    private static final String ALL_USER_CODE = "A00701";
    private static final String NON_MEMBER_CODE = "A00702";
    private static final String MEMBER_CODE = "A00703";
    private static final String EXCELLENT_MEMBER_CODE = "A00704";
    private static final String SPECIAL_MEMBER_CODE = "A00705";
    private static final String ADMIN_USER_CODE = "A00706";

    AUTHORITY_CODE(Code code)
    {
        this.code = code;
    }

    private enum Code
    {
        A00701
        {
            String getCode()
            {
                return ALL_USER_CODE;
            }
        },

        A00702
        {
            String getCode()
            {
                return NON_MEMBER_CODE;
            }
        },

        A00703
        {
            String getCode()
            {
                return MEMBER_CODE;
            }
        },

        A00704
        {
            String getCode()
            {
                return EXCELLENT_MEMBER_CODE;
            }
        },

        A00705
        {
            String getCode()
            {
                return SPECIAL_MEMBER_CODE;
            }
        },

        A00706
        {
            String getCode()
            {
                return ADMIN_USER_CODE;
            }
        },

        A007
        {
            String getCode()
            {
                return PRIMARY_CODE;
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