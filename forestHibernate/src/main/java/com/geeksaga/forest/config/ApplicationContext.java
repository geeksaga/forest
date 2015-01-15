package com.geeksaga.forest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

//@Configuration
//@ComponentScan(basePackages = { "com.geeksaga.forest" })
//@EnableWebMvc
//@Import({ DataConfig.class })
//@ImportResource({ "classpath:trace-context.xml" })
//@PropertySource("classpath:spring.properties")
public class ApplicationContext // extends WebMvcConfigurerAdapter
{
//    // Maps resources path to webapp/resources
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry)
//    {
//        // registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
//        registry.addResourceHandler("/css/**").addResourceLocations("/css/");
//        registry.addResourceHandler("/image/**").addResourceLocations("/image/");
//        registry.addResourceHandler("/img/**").addResourceLocations("/img/");
//        registry.addResourceHandler("/script/**").addResourceLocations("/script/");
//    }

    // Provides internationalization of messages
    @Bean
    public ResourceBundleMessageSource messageSource()
    {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("messages");

        return messageSource;
    }

    // Only needed if we are using @Value and ${...} when referencing properties
    // Otherwise @PropertySource is enough by itself
    @Bean
    public static PropertySourcesPlaceholderConfigurer properties()
    {
        PropertySourcesPlaceholderConfigurer propertySources = new PropertySourcesPlaceholderConfigurer();
        Resource[] resources = new ClassPathResource[] { new ClassPathResource("spring.properties") };
        propertySources.setLocations(resources);
        propertySources.setIgnoreUnresolvablePlaceholders(true);

        return propertySources;
    }
}