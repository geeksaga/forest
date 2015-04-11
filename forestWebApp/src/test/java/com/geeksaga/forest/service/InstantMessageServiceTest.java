package com.geeksaga.forest.service;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.integration.xmpp.XmppHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.GenericMessage;

@Ignore
public class InstantMessageServiceTest
{
    @Test
    public void runDemo() throws Exception
    {
        ApplicationContext context = new ClassPathXmlApplicationContext("InstantMessage.xml");

        Message<String> xmppOutboundMsg = MessageBuilder.withPayload("Hello, XMPP!").setHeader(XmppHeaders.CHAT, "userhandle")
        // .setHeader(XmppHeaders.FROM, "sns@geeksaga.com")
        // .setHeader(XmppHeaders.TO, "smileemaker@gmail.com")
                .build();

        MessageChannel toUserChannel = context.getBean("toUserChannel", MessageChannel.class);

        Message<String> message = new GenericMessage<String>("Hello from Spring Integration XMPP");

        toUserChannel.send(xmppOutboundMsg);
        // toUserChannel.send(message);
    }
}