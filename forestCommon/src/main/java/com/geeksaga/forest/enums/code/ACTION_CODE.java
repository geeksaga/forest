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

public enum ACTION_CODE
{
    SUCCESS(Code.A00101), //
    FAIL(Code.A00102), //
    PRIMARYCODE(Code.A001);

    private Code code;
    private static final String PRIMARY_CODE = "A001";
    private static final String SUCCESS_CODE = "A00101";
    private static final String FAIL_CODE = "A00102";

    ACTION_CODE(Code code)
    {
        this.code = code;
    }

    private enum Code
    {
        A00101
        {
            String getCode()
            {
                return SUCCESS_CODE;
            }
        },

        A00102
        {
            String getCode()
            {
                return FAIL_CODE;
            }
        },

        A001
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
}