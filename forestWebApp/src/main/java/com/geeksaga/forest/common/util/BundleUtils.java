package com.geeksaga.forest.common.util;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.springframework.context.support.MessageSourceAccessor;

/**
 * @author geeksaga
 * @since 0.1
 */
public class BundleUtils
{
    private static MessageSourceAccessor messageSourceAccessor;

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
        return getString(bundleName, key, Locale.KOREA);
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

    public static String getMessage(String code, Locale locale)
    {
        return messageSourceAccessor.getMessage(code, locale);
    }

    public static String getMessage(String code)
    {
        return messageSourceAccessor.getMessage(code);
    }
}