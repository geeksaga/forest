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

import java.io.IOException;

import javax.annotation.PostConstruct;

import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.SmackException.NotConnectedException;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Presence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JabberService
{
    protected static final Logger logger = LoggerFactory.getLogger(JabberService.class);

    // @Autowired(required = false) PacketListenerRepository listenerRepository;
    
    @Autowired
    public XMPPConnection connection;

    @Value(value = "${spring.xmpp.user.login}")
    private String username;
    
    @Value(value = "${spring.xmpp.user.password}")
    private String password;

    boolean runOnStart;

    @PostConstruct
    public void startService()
    {
        if (runOnStart)
        {
            try
            {
                login();
            }
            catch (XMPPException | SmackException | IOException e)
            {
                logger.info(e.getMessage(), e);
            }

            addListener();
        }
    }

    public JabberService()
    {}

    public JabberService(String username, String password, boolean runOnStart)
    {
        this.username = username;
        this.password = password;
        this.runOnStart = runOnStart;
    }

    public void sendMessage(String address, String message)
    {
        try
        {
            login();
            makeAvailable();
            // for (String address : ssm.getMessageReceivers()) {
            Message msg = new Message(address, Message.Type.chat);
            // String message = ssm.getMessage();
            msg.setBody(message);
            connection.sendPacket(msg);
            // }
            makeAvailable();
        }
        catch (XMPPException | SmackException | IOException e)
        {
            logger.info("구글 토크 메시지 전송 에러 error: [{}]", e);
        }
    }

    private void makeAvailable()
    {
        Presence presence = new Presence(Presence.Type.available);
        try
        {
            connection.sendPacket(presence);
        }
        catch (NotConnectedException e)
        {
            e.printStackTrace();
        }
    }

    private void login() throws XMPPException, SmackException, IOException
    {
        if (!connection.isConnected())
        {
            connection.connect();
        }
        
        if (!connection.isAuthenticated())
        {
            connection.login(username, password);
        }
    }

    private void addListener()
    {
        // for (PacketListener listener : listenerRepository.getAll())
        // connection.addPacketListener(listener, null);
    }
}