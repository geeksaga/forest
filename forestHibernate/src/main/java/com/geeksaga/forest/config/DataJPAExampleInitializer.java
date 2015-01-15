package com.geeksaga.forest.config;


public class DataJPAExampleInitializer // implements WebApplicationInitializer
{
    private static final String DISPATCHER_SERVLET_NAME = "dispatcher";
    private static final String DISPATCHER_SERVLET_MAPPING = "/";

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
}
