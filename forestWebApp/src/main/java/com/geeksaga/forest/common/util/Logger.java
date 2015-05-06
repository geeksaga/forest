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
package com.geeksaga.forest.common.util;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.slf4j.LoggerFactory;

public class Logger
{
    public static void debug(String msg)
    {
        LoggerFactory.getLogger(getCallersClassName()).debug(msg);
    }

    public static void info(String msg)
    {
        LoggerFactory.getLogger(getCallersClassName()).info(msg);
    }

    public static void warn(Throwable thr)
    {
        LoggerFactory.getLogger(getCallersClassName()).warn(toString(thr));
    }

    public static void warn(String msg)
    {
        LoggerFactory.getLogger(getCallersClassName()).warn(msg);
    }

    public static void error(Throwable thr)
    {
        LoggerFactory.getLogger(getCallersClassName()).error(toString(thr));
    }

    public static void error(String msg)
    {
        LoggerFactory.getLogger(getCallersClassName()).error(msg);
    }

    private static String toString(Throwable thr)
    {
        return getStackTrace(thr).trim();
    }

    private static String getCallersClassName()
    {
        try
        {
            return Thread.currentThread().getStackTrace()[3].getClassName();
        }
        catch (Exception e)
        {
            LoggerFactory.getLogger(Logger.class).error(toString(e));

            return "Unknown";
        }
    }

    public static String getStackTrace(Throwable thr)
    {
        if (thr != null)
        {
            StringWriter StringWriter = new StringWriter();
            PrintWriter printWriter = new PrintWriter(StringWriter);
            thr.printStackTrace(printWriter);

            printWriter.close();

            return StringWriter.toString();
        }

        return "";
    }
}