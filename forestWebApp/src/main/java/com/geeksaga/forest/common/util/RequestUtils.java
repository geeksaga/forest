package com.geeksaga.forest.common.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

/**
 * @author geeksaga
 * @version 0.1
 */
public class RequestUtils
{
    public static HttpServletRequest getRequest(WebRequest request)
    {
        return ((ServletWebRequest) request).getRequest();
    }

    public static HttpSession getSession(WebRequest request)
    {
        return getRequest(request).getSession();
    }

    public static String getRemoteAddr(WebRequest request)
    {
        return getRequest(request).getRemoteAddr();
    }
}