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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;

public class MessageUtils
{
    private static final Logger logger = LoggerFactory.getLogger(MessageUtils.class);

    private MessageSourceAccessor messageSourceAccessor;

    public MessageUtils(MessageSource messageSource)
    {
        this.messageSourceAccessor = new MessageSourceAccessor(messageSource);
    }
    
    public String getMessage(String code)
    {
        return messageSourceAccessor.getMessage(code);
    }

    public String getMessage(String code, Locale locale)
    {
        return messageSourceAccessor.getMessage(code, locale);
    }
    
    public String getMessage(String code, String[] args, Locale locale)
    {
        return messageSourceAccessor.getMessage(code, args, locale);
    }

    public String toString()
    {
        return "MessageUtils []";
    }
}