package com.geeksaga.forest.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@PropertySources({
        @PropertySource("classpath:application.properties")
})
@ComponentScan(basePackages = { "com.geeksaga.forest.repository.querydsl" })
@EnableTransactionManagement
public class HibernateConfiguration
{

}
