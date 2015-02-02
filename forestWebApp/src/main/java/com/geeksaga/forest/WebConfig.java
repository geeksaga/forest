package com.geeksaga.forest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.Ordered;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
// @EnableWebMvc
@Import({ ThymeleafConfig.class })
public class WebConfig extends WebMvcConfigurerAdapter
{
    // @Override
    // public void addResourceHandlers(ResourceHandlerRegistry registry)
    // {
    // registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    // }
    //
    // @Override
    // public void addInterceptors(InterceptorRegistry registry)
    // {
    // LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
    // localeChangeInterceptor.setParamName("lang");
    // registry.addInterceptor(localeChangeInterceptor);
    // }
    //
    // @Bean
    // public LocaleResolver localeResolver()
    // {
    // CookieLocaleResolver cookieLocaleResolver = new CookieLocaleResolver();
    // cookieLocaleResolver.setDefaultLocale(StringUtils.parseLocaleString("en"));
    // return cookieLocaleResolver;
    // }
    //
    @Override
    public void addViewControllers(ViewControllerRegistry registry)
    {
        registry.addViewController("/login").setViewName("login");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
    }

    // @Bean
    // public MultipartResolver multipartResolver() {
    // CommonsMultipartResolver resolver = new CommonsMultipartResolver();
    // resolver.setMaxUploadSize(100000);
    // return resolver;
    // }

    @Bean
    public MultipartResolver multipartResolver()
    {
        return new StandardServletMultipartResolver();
    }

    // @Bean
    // public MessageSource messageSource()
    // {
    // ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
    // messageSource.setBasenames("classpath:messages/messages", "classpath:messages/validation");
    // messageSource.setUseCodeAsDefaultMessage(true);
    // messageSource.setDefaultEncoding("UTF-8");
    // // # -1 : never reload, 0 always reload
    // messageSource.setCacheSeconds(0);
    // return messageSource;
    // }
    //
    // @Override
    // public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer)
    // {
    // configurer.enable();
    // }
    //
    // @Bean
    // public InternalResourceViewResolver viewResolver()
    // {
    // InternalResourceViewResolver resolver = new InternalResourceViewResolver();
    // resolver.setPrefix("/WEB-INF/views/");
    // resolver.setSuffix(".jsp");
    // return resolver;
    // }

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