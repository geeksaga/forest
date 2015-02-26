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

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.hibernate.jpa.HibernateEntityManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.hibernate4.HibernateExceptionTranslator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@PropertySources({ @PropertySource("classpath:application.properties"), @PropertySource("classpath:spring.properties") })
@Configuration
@ComponentScan(basePackages = { "com.geeksaga.forest.dao", "com.geeksaga.forest.repositories.jpa", "com.geeksaga.forest.entity",
        "com.geeksaga.forest.repositories.querydsl" })
@EnableTransactionManagement
@EnableJpaRepositories("com.geeksaga.forest.repositories")
public class DataConfig
{
    @Autowired
    private Environment env;

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() throws IOException
    {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public DataSource dataSource()
    {
        return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.HSQL).build();

        // try
        // {
        // ComboPooledDataSource ds = new ComboPooledDataSource();
        // ds.setDriverClass(env.getRequiredProperty("db.driver"));
        // ds.setJdbcUrl(env.getRequiredProperty("db.url"));
        // ds.setUser(env.getRequiredProperty("db.username"));
        // ds.setPassword(env.getRequiredProperty("db.password"));
        // ds.setAcquireIncrement(5);
        // ds.setIdleConnectionTestPeriod(60);
        // ds.setMaxPoolSize(100);
        // ds.setMaxStatements(50);
        // ds.setMinPoolSize(10);
        //
        // return ds;
        // }
        // catch (Exception e)
        // {
        // throw new RuntimeException(e);
        // }
    }

    @Bean
    public JpaTransactionManager transactionManager(EntityManagerFactory emf)
    {
        return new JpaTransactionManager(emf);
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter()
    {
        HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        jpaVendorAdapter.setDatabase(Database.HSQL);
        jpaVendorAdapter.setGenerateDdl(true);
        jpaVendorAdapter.setShowSql(true);

        return jpaVendorAdapter;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory()
    {
        LocalContainerEntityManagerFactoryBean lemfb = new LocalContainerEntityManagerFactoryBean();
        lemfb.setDataSource(dataSource());
        lemfb.setJpaVendorAdapter(jpaVendorAdapter());
        lemfb.setPackagesToScan("com.geeksaga");
        // lemfb.setPersistenceUnitName("jpa.sample.plain");

        return lemfb;
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