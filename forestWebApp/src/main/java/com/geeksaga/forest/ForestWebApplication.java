package com.geeksaga.forest;

import java.nio.charset.Charset;

import javax.servlet.Filter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.filter.CharacterEncodingFilter;

import com.geeksaga.forest.repositories.entity.User;
import com.geeksaga.forest.repositories.jpa.auditing.AuditableUser;

// @ComponentScan(basePackages = { "com.geeksaga.forest" }, excludeFilters = @ComponentScan.Filter(type = FilterType.REGEX, pattern = {
// "com.geeksaga.forest.web.*" }))

@ComponentScan(basePackages = { "com.geeksaga.forest", "com.geeksaga.forest.repositories", "com.geeksaga.forest.service",
        "com.geeksaga.forest.controller" })
@SpringBootApplication
@EntityScan(basePackageClasses = { User.class, AuditableUser.class })
public class ForestWebApplication extends SpringBootServletInitializer
{
    // private static final String DISPATCHER_SERVLET_NAME = "dispatcher";
    // private static final String DISPATCHER_SERVLET_MAPPING = "/";
    //
    // @Override
    // public void onStartup(ServletContext servletContext) throws ServletException
    // {
    // AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
    // rootContext.register(ApplicationContext.class);
    //
    // ServletRegistration.Dynamic dispatcher = servletContext.addServlet(DISPATCHER_SERVLET_NAME, new DispatcherServlet(rootContext));
    // dispatcher.setLoadOnStartup(1);
    // dispatcher.addMapping(DISPATCHER_SERVLET_MAPPING);
    //
    // servletContext.addListener(new ContextLoaderListener(rootContext));
    // }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application)
    {
        return application.sources(ForestWebApplication.class);
    }

    @Bean
    public HttpMessageConverter<String> responseBodyConverter()
    {
        return new StringHttpMessageConverter(Charset.forName("UTF-8"));
    }

    @Bean
    public Filter characterEncodingFilter()
    {
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);

        return characterEncodingFilter;
    }

    // @Bean
    // public JavaMailSenderImpl javaMailSenderImpl()
    // {
    // JavaMailSenderImpl mailSenderImpl = new JavaMailSenderImpl();
    // mailSenderImpl.setHost(env.getProperty("smtp.host"));
    // mailSenderImpl.setPort(env.getProperty("smtp.port", Integer.class));
    // mailSenderImpl.setProtocol(env.getProperty("smtp.protocol"));
    // mailSenderImpl.setUsername(env.getProperty("smtp.username"));
    // mailSenderImpl.setPassword(env.getProperty("smtp.password"));
    // Properties javaMailProps = new Properties();
    // javaMailProps.put("mail.smtp.auth", true);
    // javaMailProps.put("mail.smtp.starttls.enable", true);
    // mailSenderImpl.setJavaMailProperties(javaMailProps);
    // return mailSenderImpl;
    // }

    @Bean
    public CacheManager cacheManager()
    {
        return new ConcurrentMapCacheManager();
    }

    public static void main(String[] args) throws Exception
    {
        SpringApplication.run(ForestWebApplication.class, args);
    }
}
