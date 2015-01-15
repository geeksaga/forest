package com.geeksaga.forest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

//@Configuration
//@ComponentScan
//@EnableAutoConfiguration
@SpringBootApplication
public class ForestHibernateApplication
{
    public static void main(String[] args)
    {
        ConfigurableApplicationContext context = SpringApplication.run(ForestHibernateApplication.class);
        // CustomerRepository repository = context.getBean(CustomerRepository.class);

        // SpringApplication.run(ForestHibernateApplication.class, args);
        
        context.close();
    }
}