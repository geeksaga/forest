package com.geeksaga.forest.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

//@Configuration
//@EnableWebMvc
//@ComponentScan(basePackages = "com.geeksaga.forest.controller")
public class WebConfig extends WebMvcConfigurerAdapter
{
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer)
    {
        configurer.enable();
    }

    // @Bean(name = "multipartResolver")
    // public CommonsMultipartResolver multipartResolver() {
    // return new CommonsMultipartResolver();
    // }

//    @Bean
//    public InternalResourceViewResolver viewResolver()
//    {
//        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
//        resolver.setPrefix("/WEB-INF/jsp/");
//        resolver.setSuffix(".jsp");
//        
//        return resolver;
//    }

    /*
     * @Bean public SpringTemplateLoader templateLoader() { SpringTemplateLoader templateLoader = new SpringTemplateLoader();
     * templateLoader.setBasePath("/WEB-INF/views/"); templateLoader.setEncoding("UTF-8"); templateLoader.setSuffix(".jade"); return
     * templateLoader; }
     * 
     * @Bean public JadeConfiguration jadeConfiguration() { JadeConfiguration configuration = new JadeConfiguration();
     * configuration.setCaching(false); configuration.setTemplateLoader(templateLoader()); return configuration; }
     * 
     * @Bean public ViewResolver viewResolver() { JadeViewResolver viewResolver = new JadeViewResolver();
     * viewResolver.setConfiguration(jadeConfiguration()); return viewResolver; }
     */

}
