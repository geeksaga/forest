package com.geeksaga.forest;

import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

// http://www.thymeleaf.org/doc/tutorials/2.1/thymeleafspring.html
// @Configuration
public class ThymeleafConfig
{
    // @Bean
    public ServletContextTemplateResolver templateResolver()
    {
        ServletContextTemplateResolver resolver = new ServletContextTemplateResolver();
        resolver.setCharacterEncoding("UTF-8");
        resolver.setPrefix("/WEB-INF/templates/");
        resolver.setSuffix(".html");
        resolver.setTemplateMode("HTML5");
        resolver.setCacheable(false);

        return resolver;
    }

    // @Bean
    public SpringTemplateEngine templateEngine()
    {
        SpringTemplateEngine engine = new SpringTemplateEngine();
        engine.setTemplateResolver(templateResolver());

        return engine;
    }

    // @Bean
    public ThymeleafViewResolver thymeleafViewResolver()
    {
        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
        resolver.setTemplateEngine(templateEngine());
        resolver.setCharacterEncoding("UTF-8");

        return resolver;
    }
}
