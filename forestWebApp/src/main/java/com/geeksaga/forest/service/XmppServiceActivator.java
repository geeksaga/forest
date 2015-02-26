package com.geeksaga.forest.service;

import org.jivesoftware.smack.Chat;
import org.springframework.integration.xmpp.XmppHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;

// @Component
public class XmppServiceActivator
{
    // @ServiceActivator
    public void onIM(@Header(XmppHeaders.CHAT) Chat chat, @Payload String txt) throws Exception
    {
        System.out.println("Recieved message '" + txt + "' from " + chat.getParticipant());
    }

    // public static void main(String[] args) throws Exception
    // {
    // new ClassPathXmlApplicationContext("config.xml").start();
    //
    // while (true)
    // {
    // Thread.sleep(1000);
    // }
    // }
}