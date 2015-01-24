package com.geeksaga.forest.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.mchange.v2.c3p0.ComboPooledDataSource;

//@Configuration
////@PropertySources({
////    @PropertySource("classpath:connection.properties")
////})
//@EnableJpaRepositories(basePackages = { "com.geeksaga.forest.reposiptory" })
//@EnableTransactionManagement
public class PersistenceContext
{
    // @Bean
    // LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource, Environment env)
    // {
    // LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
    // entityManagerFactoryBean.setDataSource(dataSource);
    // entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
    // entityManagerFactoryBean.setPackagesToScan("com.geeksaga.forest.entity");
    //
    // Properties jpaProperties = new Properties();
    //
    // // Configures the used database dialect. This allows Hibernate to create SQL
    // // that is optimized for the used database.
    // jpaProperties.put("hibernate.dialect", env.getRequiredProperty("hibernate.dialect"));
    //
    // // Specifies the action that is invoked to the database when the Hibernate
    // // SessionFactory is created or closed.
    // jpaProperties.put("hibernate.hbm2ddl.auto", env.getRequiredProperty("hibernate.hbm2ddl.auto"));
    //
    // // Configures the naming strategy that is used when Hibernate creates
    // // new database objects and schema elements
    // jpaProperties.put("hibernate.ejb.naming_strategy", env.getRequiredProperty("hibernate.ejb.naming_strategy"));
    //
    // // If the value of this property is true, Hibernate writes all SQL
    // // statements to the console.
    // jpaProperties.put("hibernate.show_sql", env.getRequiredProperty("hibernate.show_sql"));
    //
    // // If the value of this property is true, Hibernate will format the SQL
    // // that is written to the console.
    // jpaProperties.put("hibernate.format_sql", env.getRequiredProperty("hibernate.format_sql"));
    //
    // entityManagerFactoryBean.setJpaProperties(jpaProperties);
    //
    // return entityManagerFactoryBean;
    // }
    //
    // @Bean
    // JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory)
    // {
    // JpaTransactionManager transactionManager = new JpaTransactionManager();
    // transactionManager.setEntityManagerFactory(entityManagerFactory);
    // return transactionManager;
    // }
    //
    // @Bean(destroyMethod = "close")
    // DataSource dataSource(Environment env)
    // {
    // try
    // {
    // ComboPooledDataSource ds = new ComboPooledDataSource();
    // ds.setDriverClass(env.getRequiredProperty("app.jdbc.driverClassName"));
    // ds.setJdbcUrl(env.getRequiredProperty("app.jdbc.url"));
    // ds.setUser(env.getRequiredProperty("app.jdbc.username"));
    // ds.setPassword(env.getRequiredProperty("app.jdbc.password"));
    // ds.setAcquireIncrement(5);
    // ds.setIdleConnectionTestPeriod(60);
    // ds.setMaxPoolSize(100);
    // ds.setMaxStatements(50);
    // ds.setMinPoolSize(10);
    // return ds;
    // }
    // catch (Exception e)
    // {
    // throw new RuntimeException(e);
    // }
    // // HikariConfig dataSourceConfig = new HikariConfig();
    // // dataSourceConfig.setDriverClassName(env.getRequiredProperty("db.driver"));
    // // dataSourceConfig.setJdbcUrl(env.getRequiredProperty("db.url"));
    // // dataSourceConfig.setUsername(env.getRequiredProperty("db.username"));
    // // dataSourceConfig.setPassword(env.getRequiredProperty("db.password"));
    // //
    // // return new HikariDataSource(dataSourceConfig);
    // }
}