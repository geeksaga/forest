package com.geeksaga.forest.config;


public class ApplicationInitializer // implements WebApplicationInitializer
{
//    @Override
//    public void onStartup(ServletContext servletContext) throws ServletException
//    {
//        // Load application context
//        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
//        context.register(ApplicationContext.class);
//        context.setDisplayName("GeekSaga - Forest");
//
//        // Add context loader listener
//        servletContext.addListener(new ContextLoaderListener(context));
//
//        AnnotationConfigWebApplicationContext dispatcherServletContext = new AnnotationConfigWebApplicationContext();
//        dispatcherServletContext.register(WebConfig.class);
//        
//        // Declare dispatcher servlet
//        ServletRegistration.Dynamic dispatcher = servletContext.addServlet("dispatcher", new DispatcherServlet(dispatcherServletContext));
//        dispatcher.setLoadOnStartup(1);
//        dispatcher.addMapping("/");
//
//        servletContext.addFilter("Spring OpenEntityManagerInViewFilter", OpenEntityManagerInViewFilter.class)
//        .addMappingForUrlPatterns(null, false, "/*");
//        
//        servletContext.addFilter("HttpMethodFilter", HiddenHttpMethodFilter.class).addMappingForUrlPatterns(null,
//                false, "/*");
//        
//        FilterRegistration charEncodingfilterReg = servletContext.addFilter("CharacterEncodingFilter",
//                CharacterEncodingFilter.class);
//        charEncodingfilterReg.setInitParameter("encoding", "UTF-8");
//        charEncodingfilterReg.setInitParameter("forceEncoding", "true");
//        charEncodingfilterReg.addMappingForUrlPatterns(null, false, "/*");
//        
//        // // Register Spring security filter
//        // FilterRegistration.Dynamic springSecurityFilterChain = servletContext.addFilter("springSecurityFilterChain",
//        // DelegatingFilterProxy.class);
//        // springSecurityFilterChain.addMappingForUrlPatterns(null, false, "/*");
//        //
//        // // Register Spring Social filter so that we can disconnect from providers
//        // FilterRegistration.Dynamic hiddenHttpMethodFilter = servletContext
//        // .addFilter("hiddenHttpMethodFilter", HiddenHttpMethodFilter.class);
//        // hiddenHttpMethodFilter.addMappingForUrlPatterns(null, false, "/*");
//        
//    }
}