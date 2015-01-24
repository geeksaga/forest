package com.geeksaga.forest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/**
 * @author geeksaga
 * @version 0.1
 */
@Configuration
@ImportResource({ "classpath*:/simple-repository-context.xml" })
public class SpringConfig
{
    @Bean
    public PropertySourcesPlaceholderConfigurer properties()
    {
        return new PropertySourcesPlaceholderConfigurer();
    }
}