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
package com.geeksaga.forest;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class CustomizationBean implements EmbeddedServletContainerCustomizer
{
    @Value("${server.port}")
    private int port;

    // @Value("${keystore.file}")
    // Resource keystoreFile;
    // @Value("${keystore.alias}")
    // String alias;
    // @Value("${keystore.password}")
    // String keystorePass;
    // @Value("${keystore.type}")
    // String keystoreType;

    @Override
    public void customize(ConfigurableEmbeddedServletContainer container)
    {
        // final String absoluteKeystoreFile = keystoreFile.getFile().getAbsolutePath();

        container.setPort(port);
        // connector.setSecure(true);
        // connector.setScheme("https");
        // Http11NioProtocol proto = (Http11NioProtocol) connector.getProtocolHandler();
        // proto.setSSLEnabled(true);
        // proto.setKeystoreFile(absoluteKeystoreFile);
        // proto.setKeyAlias(alias);
        // proto.setKeystorePass(keystorePass);
        // proto.setKeystoreType(keystoreType);
    }

    @Bean
    public EmbeddedServletContainerFactory servletContainer()
    {
        TomcatEmbeddedServletContainerFactory factory = new TomcatEmbeddedServletContainerFactory();
        factory.setPort(port);
        factory.setSessionTimeout(10, TimeUnit.MINUTES);
        // factory.addErrorPages(new ErrorPage(HttpStatus.404, "/notfound.html");
        return factory;
    }
}