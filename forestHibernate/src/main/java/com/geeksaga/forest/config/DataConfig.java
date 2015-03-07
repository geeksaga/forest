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
package com.geeksaga.forest.config;

import java.io.IOException;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.hibernate.jpa.HibernateEntityManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate4.HibernateExceptionTranslator;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.geeksaga.forest.repositories.jpa.auditing.AuditorAwareImpl;

@PropertySources({ @PropertySource("classpath:application.properties"), @PropertySource("classpath:spring.properties") })
@Configuration
@ComponentScan(basePackages = { "com.geeksaga.forest.dao", "com.geeksaga.forest.repositories.jpa", "com.geeksaga.forest.entity",
        "com.geeksaga.forest.repositories.querydsl" })
@EnableTransactionManagement
@EnableJpaRepositories("com.geeksaga.forest.repositories")
@EnableAutoConfiguration
@EnableJpaAuditing
public class DataConfig
{
    @Autowired
    private Environment env;

    @Autowired
    private JpaVendorAdapter adapter;

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() throws IOException
    {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource)
    {
        LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        localContainerEntityManagerFactoryBean.setMappingResources("META-INF/orm.xml");
        localContainerEntityManagerFactoryBean.setDataSource(dataSource);
        localContainerEntityManagerFactoryBean.setJpaVendorAdapter(adapter);
        localContainerEntityManagerFactoryBean.setPackagesToScan("com.geeksaga");
        localContainerEntityManagerFactoryBean.setPersistenceUnitName("jpa.sample.plain");

        return localContainerEntityManagerFactoryBean;
    }

    @Bean
    public AuditorAwareImpl auditorAware()
    {
        return new AuditorAwareImpl();
    }

    @Bean
    public HibernateExceptionTranslator hibernateExceptionTranslator()
    {
        return new HibernateExceptionTranslator();
    }

    @Bean
    public SessionFactory sessionFactory(HibernateEntityManagerFactory hibernateEntityManagerFactory)
    {
        return hibernateEntityManagerFactory.getSessionFactory();
    }
}