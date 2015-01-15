package com.geeksaga.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.geeksaga.common.enums.DateTimeFormatList;

/**
 * @author geeksaga
 * @since 0.1
 * @version 0.1
 */
public class DateConvertor
{
    public static String getDateFormat()
    {
        return getDateFormat(new Date());
    }

    public static String getDateFormat(Date date)
    {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DateTimeFormatList.YYYYMMDD.getDateTimeFormat());

        return simpleDateFormat.format(date);
    }

    public static String getDateFormat(DateTimeFormatList dateTimeFormat)
    {
        return getDateFormat(new Date(), dateTimeFormat);
    }

    public static String getDateFormat(Date date, DateTimeFormatList dateTimeFormat)
    {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateTimeFormat.getDateTimeFormat());

        return simpleDateFormat.format(date);
    }

    public static String getTimeFormat()
    {
        return getTimeFormat(new Date());
    }

    public static String getTimeFormat(Date date)
    {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DateTimeFormatList.HHMMSS.getDateTimeFormat());

        return simpleDateFormat.format(date);
    }

    public static String getTimeFormat(Date date, DateTimeFormatList dateTimeFormat)
    {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateTimeFormat.getDateTimeFormat());

        return simpleDateFormat.format(date);
    }

    public static String getDateTimeFormat()
    {
        return getDateTimeFormat(new Date());
    }

    public static String getDateTimeFormat(Date date, DateTimeFormatList dateTimeFormat)
    {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateTimeFormat.getDateTimeFormat());

        return simpleDateFormat.format(date);
    }

    public static String getDateTimeFormat(Date date)
    {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DateTimeFormatList.YYYYMMDDHHMMSS.getDateTimeFormat());

        return simpleDateFormat.format(date);
    }

    public static Date getDateTimeFormat(String date)
    {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DateTimeFormatList.YYYYMMDDHHMMSS.getDateTimeFormat());

        try
        {
            return simpleDateFormat.parse(date);
        }
        catch (ParseException e)
        {
            return null;
        }
    }

    public static String getDateTimeMilllisFormat()
    {
        return getDateTimeFormat(new Date());
    }

    public static String getDateTimeMilllisFormat(Date date)
    {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DateTimeFormatList.YYYYMMDDHHMMSSSSS.getDateTimeFormat());

        return simpleDateFormat.format(date);
    }

    public static Date getDateTimeMilllisFormat(String date)
    {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DateTimeFormatList.YYYYMMDDHHMMSSSSS.getDateTimeFormat());

        try
        {
            return simpleDateFormat.parse(date);
        }
        catch (ParseException e)
        {
            return null;
        }
    }
}