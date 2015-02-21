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
package com.geeksaga.forest.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;

import com.geeksaga.forest.entity.User;
import com.geeksaga.forest.message.Message;
import com.geeksaga.forest.message.SignupMessage;

@Component
public class SendMailService implements Runnable
{
    private static final Logger logger = LoggerFactory.getLogger(SendMailService.class);

    @Autowired
    private JavaMailSender mailSender;

    private Message message;
    
    @Value(value = "${spring.mail.default-encoding}")
    private String defaultEncoding;

    public SendMailService()
    {}

    public SendMailService(SignupMessage message)
    {
        this.message = message;
    }

    public void setMessage(Message message)
    {
        this.message = message;
    }

    public void sendMail(Message message)
    {
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        if (message.getReceivers().length == 0)
        {
            return;
        }

        if (message.getReceivers().length > 100)
        {
            for (String to : message.getReceivers())
            {
                sendMail(to, message, mimeMessage);
            }
        }
        else
        {
            sendMail(message, mimeMessage);
        }
    }

    private void sendMail(Message message, MimeMessage mimeMessage2)
    {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, defaultEncoding);

        try
        {
            helper.setTo(message.getReceivers());
            helper.setFrom(message.getFrom());
            helper.setSubject(message.getTitle());
            helper.setText(message.getMessage(), message.isHTML());
        }
        catch (MessagingException e)
        {
            logger.info(e.getMessage());
        }

        mailSender.send(mimeMessage);
    }

    private void sendMail(String to, Message message, MimeMessage mimeMessage)
    {
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, defaultEncoding);

        try
        {
            helper.setTo(to);
            helper.setFrom(message.getFrom());
            helper.setSubject(message.getTitle());
            helper.setText(message.getMessage(), message.isHTML());
        }
        catch (MessagingException e)
        {
            logger.info(e.getMessage());
        }

        mailSender.send(mimeMessage);
    }

    @Deprecated
    protected void sendConfirmationEmail(final User user)
    {
        MimeMessagePreparator preparator = new MimeMessagePreparator()
        {
            public void prepare(MimeMessage mimeMessage) throws Exception
            {
                MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
                message.setTo(user.getEmail());
                // message.setFrom(message.getFrom());
                // message.setSubject(message.getTitle());
                // message.setText(message.getMessage(), message.isHTML());
            }
        };

        mailSender.send(preparator);
    }

    public void run()
    {
        sendMail(this.message);
    }
}