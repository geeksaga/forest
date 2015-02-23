package com.geeksaga.forest;

import java.nio.charset.Charset;
import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.SASLAuthentication;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
// import org.jivesoftware.smack.ConnectionConfiguration;
// import org.jivesoftware.smack.SASLAuthentication;
// import org.jivesoftware.smack.XMPPConnection;
// import org.jivesoftware.smack.tcp.XMPPTCPConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.core.task.TaskExecutor;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.filter.CharacterEncodingFilter;

import com.geeksaga.forest.common.util.MessageUtils;
import com.geeksaga.forest.entity.User;
import com.geeksaga.forest.repositories.jpa.auditing.AuditableUser;

// @ComponentScan(basePackages = { "com.geeksaga.forest" }, excludeFilters = @ComponentScan.Filter(type = FilterType.REGEX, pattern = {
// "com.geeksaga.forest.web.*" }))

@ComponentScan(basePackages = { "com.geeksaga.forest", "com.geeksaga.forest.service", "com.geeksaga.forest.controller",
        "com.geeksaga.forest.service.dev", "com.geeksaga.forest.controller.dev" })
@PropertySources({ @PropertySource("classpath:application.properties"),
        @PropertySource("classpath:application-${spring.profiles.active}.properties") })
@SpringBootApplication
// @ImportResource("classpath:InstantMessage.xml")
@EntityScan(basePackageClasses = { User.class, AuditableUser.class })
public class ForestWebApplication extends SpringBootServletInitializer
{
    private static final Logger logger = LoggerFactory.getLogger(ForestWebApplication.class);

    @Autowired
    private Environment env;

    @Autowired
    private MessageSource messageSource;

    // @Override
    // public void onStartup(ServletContext servletContext) throws ServletException
    // {
    // AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
    // rootContext.register(ApplicationContext.class);
    //
    // ServletRegistration.Dynamic dispatcher = servletContext.addServlet("dispatcher", new DispatcherServlet(rootContext));
    // dispatcher.setLoadOnStartup(1);
    // dispatcher.addMapping("/");
    //
    // servletContext.addListener(new ContextLoaderListener(rootContext));
    // }

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException
    {
        super.onStartup(servletContext);

        String activeProfile = System.getProperty("spring.profiles.active");
        if (activeProfile == null)
        {
            activeProfile = "dev";
        }

        servletContext.setInitParameter("spring.profiles.active", activeProfile);

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

    @Bean
    public TaskExecutor taskExecutor()
    {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(5);
        taskExecutor.setMaxPoolSize(10);
        taskExecutor.setQueueCapacity(25);

        return taskExecutor;
    }

    // @Bean
    // public JavaMailSenderImpl javaMailSender()
    // {
    // Properties javaMailProps = new Properties();
    // javaMailProps.put("mail.smtp.auth", true);
    // javaMailProps.put("mail.smtp.starttls.enable", true);
    //
    // JavaMailSenderImpl javaMailSenderImpl = new JavaMailSenderImpl();
    // javaMailSenderImpl.setHost(env.getProperty("smtp.host"));
    // javaMailSenderImpl.setPort(env.getProperty("smtp.port", Integer.class));
    // javaMailSenderImpl.setProtocol(env.getProperty("smtp.protocol"));
    // javaMailSenderImpl.setUsername(env.getProperty("smtp.username"));
    // javaMailSenderImpl.setPassword(env.getProperty("smtp.password"));
    // javaMailSenderImpl.setJavaMailProperties(javaMailProps);
    //
    // return javaMailSenderImpl;
    // }

    @Bean
    public XMPPConnection googleTalkConnection() throws Exception
    {
        SASLAuthentication.supportSASLMechanism("PLAIN", 0); // static initializer

        ConnectionConfiguration config = new ConnectionConfiguration(env.getProperty("spring.xmpp.user.host"), env.getProperty(
                "spring.xmpp.user.port", Integer.class), env.getProperty("spring.xmpp.user.service"));

        XMPPConnection connection = new XMPPTCPConnection(config);
        connection.connect();
        connection.login(env.getProperty("spring.xmpp.user.login"), env.getProperty("spring.xmpp.user.password"));

        return connection;
    }

    @Bean
    public CacheManager cacheManager()
    {
        return new ConcurrentMapCacheManager();
    }

    @Bean
    public MessageUtils messageUtils()
    {
        return new MessageUtils(messageSource);
    }

    public static void main(String[] args) throws Exception
    {
        SpringApplication.run(ForestWebApplication.class, args);
    }
}