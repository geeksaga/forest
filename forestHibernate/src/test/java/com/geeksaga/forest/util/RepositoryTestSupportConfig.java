package com.geeksaga.forest.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.test.context.ContextConfiguration;

@Configuration
//@ComponentScan({ "com.geeksaga.forest" })
//@ImportResource({ "classpath:/simple-repository-context.xml" })
//@ContextConfiguration(locations = "/simple-repository-context.xml")
// @ContextConfiguration(classes = { ApplicationContext.class, DataConfig.class }, loader = AnnotationConfigContextLoader.class, locations =
// "simple-repository-context.xml")
public class RepositoryTestSupportConfig
{
    @Autowired
    private ConfigurableEnvironment configurableEnvironment;

    @Bean
    public static PropertySourcesPlaceholderConfigurer placeholderConfigurer()
    {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
