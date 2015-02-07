package com.geeksaga.forest;

import java.nio.charset.Charset;
import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;
import org.springframework.web.filter.CharacterEncodingFilter;

import com.geeksaga.forest.entity.User;
import com.geeksaga.forest.repositories.jpa.auditing.AuditableUser;

// @ComponentScan(basePackages = { "com.geeksaga.forest" }, excludeFilters = @ComponentScan.Filter(type = FilterType.REGEX, pattern = {
// "com.geeksaga.forest.web.*" }))

@ComponentScan(basePackages = { "com.geeksaga.forest", "com.geeksaga.forest.service", "com.geeksaga.forest.controller" })
@PropertySources({ @PropertySource("classpath:application.properties") })
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

    // @Override
    // public void onStartup(ServletContext container) {
    //
    // AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
    // rootContext.register(AppConfig.class);
    //
    // container.addListener(new ContextLoaderListener(rootContext));
    //
    // AnnotationConfigWebApplicationContext dispatcherContext = new AnnotationConfigWebApplicationContext();
    // dispatcherContext.register(WebConfig.class);
    //
    // MultipartConfigElement config = new MultipartConfigElement("C:\\Temp", 20848820, 418018841, 1048576);
    // DispatcherServlet dispatcherServlet = new DispatcherServlet(dispatcherContext);
    //
    // ServletRegistration.Dynamic dispatcher =
    // container.addServlet("dispatcher", dispatcherServlet);
    // dispatcher.setLoadOnStartup(1);
    // dispatcher.addMapping("/*");
    //
    // ServletRegistration.Dynamic dispatcher =
    // container.addServlet("dispatcher", dispatcherServlet);
    // dispatcher.setLoadOnStartup(1);
    // dispatcher.addMapping("/*");
    //
    // dispatcher.setMultipartConfig(new MultipartConfigElement("/tmp", 1024*1024*5, 1024*1024*5*5, 1024*1024));
    // }

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException
    {
        FilterRegistration.Dynamic openEntityManagerInViewFilter = servletContext.addFilter("openEntityManagerInViewFilter",
                new OpenEntityManagerInViewFilter());

        openEntityManagerInViewFilter.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), true, "/*");
    }

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