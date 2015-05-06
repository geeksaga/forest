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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.hibernate.validator.internal.constraintvalidators.EmailValidator;

import com.geeksaga.forest.entity.User;

public class EmailUtil
{
    public static List<String> extractMessageAllowedEmailCollectionFrom(Collection<User> users)
    {
        List<String> emails = new ArrayList<String>();
        // Iterator<User> iterator = users.iterator();

        // while (iterator.hasNext()) {
        // User User = iterator.next();
        // if (User.getIsAllowedGoogleTalk()) {
        // addIfValid(emails, User.getEmail());
        // }
        // }
        return emails;
    }

    public static String[] extractMailAllowedEmailFrom(List<User> Users)
    {
        List<String> recievers = new ArrayList<String>();
        Iterator<User> UserIterator = Users.iterator();

        while (UserIterator.hasNext())
        {
            User User = UserIterator.next();
            // if (User.getIsAllowedEmail()) {
            addIfValid(recievers, User.getEmail());
            // }
        }
        
        return recievers.toArray(new String[recievers.size()]);
    }

    public static Collection<String> extractEmailCollectionFrom(User User)
    {
        if (isNotValid(User.getEmail()))
        {
            return null;
        }
        
        return Arrays.asList(new String[] { User.getEmail() });
    }

    public static String[] extractEmailArrayForm(User User)
    {
        if (isNotValid(User.getEmail()))
        {
            return null;
        }
        
        String[] recievers = new String[1];
        recievers[0] = User.getEmail();
        
        return recievers;
    }

    private static void addIfValid(List<String> emails, String email)
    {
        if (isValid(email))
        {
            emails.add(email);
        }
    }

    private static boolean isValid(String email)
    {
        return new EmailValidator().isValid(email, null);
    }

    private static boolean isNotValid(String email)
    {
        return !isValid(email);
    }
}