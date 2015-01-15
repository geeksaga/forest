package com.geeksaga.forest.config;

import java.io.IOException;
import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

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

// import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@PropertySources({ @PropertySource("classpath:application.properties") })
@ComponentScan(basePackages = { "com.geeksaga.forest.service", "com.geeksaga.forest.dao", "com.geeksaga.forest.repositories.jpa", "com.geeksaga.forest.repository.entity",
        "com.geeksaga.forest.repository.querydsl" })
@EnableTransactionManagement
// @EnableJpaRepositories("com.geeksaga.forest.repository")
@PropertySource("classpath:spring.properties")
@EnableJpaRepositories("com.geeksaga.forest.repositories")
public class DataConfig // extends WebMvcConfigurerAdapter
{
    @Autowired
    private Environment env;

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() throws IOException
    {
        PropertySourcesPlaceholderConfigurer configHolder = new PropertySourcesPlaceholderConfigurer();
        return configHolder;
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

    // @Bean
    // public LocalContainerEntityManagerFactoryBean entityManagerFactory()
    // {
    // LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
    // em.setPackagesToScan("com.geeksaga.forest.repository.entity");
    // em.setDataSource(dataSource());
    // em.setJpaVendorAdapter(hibernateJpaVendorAdapter());
    //
    // // em.setJpaProperties(japProperties());
    // // em.setPersistenceXmlLocation("classpath*:META-INF/persistence.xml");
    // // em.setPersistenceUnitName("hibernatePersistenceUnit");
    //
    // return em;
    // }

//    @Bean
//    public Properties japProperties()
//    {
//        Properties properties = new Properties();
//        properties.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
//        return properties;
//    }

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
//        lemfb.setPersistenceUnitName("jpa.sample.plain");
        
        return lemfb;
    }

    // @Bean
    // public JpaTransactionManager transactionManager()
    // {
    // JpaTransactionManager transactionManager = new JpaTransactionManager();
    // transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
    // return transactionManager;
    // }

    // @Bean
    // public HibernateJpaVendorAdapter hibernateJpaVendorAdapter()
    // {
    // HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
    // hibernateJpaVendorAdapter.setShowSql(true);
    //
    // return hibernateJpaVendorAdapter;
    // }

    @Bean
    public HibernateExceptionTranslator hibernateExceptionTranslator()
    {
        return new HibernateExceptionTranslator();
    }
}