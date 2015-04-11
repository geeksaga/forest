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
 *
 */
package com.geeksaga.common.util;

public class HtmlUtil
{
    public static enum TAG
    {
        START, BETWEEN, END, SINGLE_QUOTE, DOUBLE_QUOTE
    };

    /**
     * HTML 문자열에서 TAG 를 제거 한다.
     * 
     * @param html
     * @return
     */
    public static String removeTag(String html)
    {
        if (html == null)
        {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        TAG state = TAG.START;
        TAG beforeState = TAG.START;
        char[] charArrary = html.toCharArray();
        char charHtml;

        for (int i = 0, j = charArrary.length; i < j; i++)
        {
            charHtml = charArrary[i];
            switch (state)
            {
                case START:
                    if (charHtml == '<')
                    {
                        state = TAG.BETWEEN;
                    }
                    else
                    {
                        sb.append(charHtml);
                    }
                    
                    break;
                case BETWEEN:
                    if (charHtml == '>')
                    {
                        state = TAG.START;
                    }
                    else if (charHtml == '\"')
                    {
                        beforeState = state;
                        state = TAG.DOUBLE_QUOTE;
                    }
                    else if (charHtml == '\'')
                    {
                        beforeState = state;
                        state = TAG.SINGLE_QUOTE;
                    }
                    else if (charHtml == '/')
                    {
                        state = TAG.END;
                    }

                    break;
                case END:
                    if (charHtml == '>')
                    {
                        state = TAG.START;
                    }
                    else if (charHtml == '\"')
                    {
                        beforeState = state;
                        state = TAG.DOUBLE_QUOTE;
                    }
                    else if (charHtml == '\'')
                    {
                        beforeState = state;
                        state = TAG.SINGLE_QUOTE;
                    }
                    else if (charHtml == '\"')
                    {
                        state = TAG.DOUBLE_QUOTE;
                    }
                    else if (charHtml == '\'')
                    {
                        state = TAG.SINGLE_QUOTE;
                    }

                    break;
                case SINGLE_QUOTE:
                    if (charHtml == '\'')
                    {
                        state = beforeState;
                    }

                    break;
                case DOUBLE_QUOTE:
                    if (charHtml == '\"')
                    {
                        state = beforeState;
                    }

                    break;
            }
        }

        return sb.toString();
    }
}