package com.geeksaga.forest.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.geeksaga.common.annotation.PrintToString;

/**
 * @author geeksaga
 * @version 0.1
 */
@Entity
@Table(name = "pw_counter", schema = "")
public class Counter extends BaseEntity implements Serializable
{
    private static final long serialVersionUID = 1L;

    @PrintToString
    private String ip;
    @PrintToString
    private String referer;
    @PrintToString
    private String browser;
    @PrintToString
    private String os;
    @PrintToString
    private String dayOfWeek;
    @PrintToString
    private String userAgent;
    @PrintToString
    private String registDate;
    @PrintToString
    private String registTime;
    @PrintToString
    private String registTimestamp;

    public String getIp()
    {
        return ip;
    }

    public void setIp(String ip)
    {
        this.ip = ip;
    }

    public String getReferer()
    {
        return referer;
    }

    public void setReferer(String referer)
    {
        this.referer = referer;
    }

    public String getBrowser()
    {
        return browser;
    }

    public void setBrowser(String browser)
    {
        this.browser = browser;
    }

    public String getOs()
    {
        return os;
    }

    public void setOs(String os)
    {
        this.os = os;
    }

    public String getDayOfWeek()
    {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek)
    {
        this.dayOfWeek = dayOfWeek;
    }

    public String getUserAgent()
    {
        return userAgent;
    }

    public void setUserAgent(String userAgent)
    {
        this.userAgent = userAgent;
    }

    public String getRegistDate()
    {
        return registDate;
    }

    public void setRegistDate(String registDate)
    {
        this.registDate = registDate;
    }

    public String getRegistTime()
    {
        return registTime;
    }

    public void setRegistTime(String registTime)
    {
        this.registTime = registTime;
    }

    public String getRegistTimestamp()
    {
        return registTimestamp;
    }

    public void setRegistTimestamp(String registTimestamp)
    {
        this.registTimestamp = registTimestamp;
    }
}