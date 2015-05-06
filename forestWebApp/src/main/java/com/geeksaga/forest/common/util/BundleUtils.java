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

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class BundleUtils
{
    private static ResourceBundle resourceBundle;

    public static Locale getLocale()
    {
        return resourceBundle.getLocale();
    }

    public static ResourceBundle getResourceBundle()
    {
        return resourceBundle;
    }

    public static void setResourceBundle(String bundleName, Locale locale)
    {
        resourceBundle = ResourceBundle.getBundle(bundleName, locale);
    }

    public static String getString(String key)
    {
        return getString(resourceBundle, key);
    }

    /**
     * @param bundleName
     * @param key
     * @return
     */
    public static String getString(String bundleName, String key)
    {
        return getString(bundleName, key, Locale.KOREAN);
    }

    /**
     * @param bundleName
     * @param key
     * @param locale
     * @return
     */
    public static String getString(String bundleName, String key, Locale locale)
    {
        resourceBundle = ResourceBundle.getBundle(bundleName, locale);

        return getString(resourceBundle, key);
    }

    private static String getString(ResourceBundle resourceBundle, String key)
    {
        String value = null;
        try
        {
            value = resourceBundle.getString(key);
        }
        catch (MissingResourceException e)
        {
            value = "[warning] ResourceBundle key  : [" + key + "] not found...";
        }

        return value;
    }
}