package com.geeksaga.forest.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.access.AccessDeniedHandler;

public class NamespaceHttpAccessDeniedHandlerTests //extends BaseSpringSpec
{
    // def "http/access-denied-handler@error-page"() {
    // when:
    // loadConfig(AccessDeniedPageConfig)
    // then:
    // findFilter(ExceptionTranslationFilter).accessDeniedHandler.errorPage == "/AccessDeniedPageConfig"
    // }
    //
    // @Configuration
    // static class AccessDeniedPageConfig extends BaseWebConfig {
    // protected void configure(HttpSecurity http) {
    // http.
    // exceptionHandling()
    // .accessDeniedPage("/AccessDeniedPageConfig")
    // }
    // }
    //
    // def "http/access-denied-handler@ref"() {
    // when:
    // loadConfig(AccessDeniedHandlerRefConfig)
    // then:
    // findFilter(ExceptionTranslationFilter).accessDeniedHandler.class == AccessDeniedHandlerRefConfig.CustomAccessDeniedHandler
    // }

    @Configuration
    static class AccessDeniedHandlerRefConfig //extends BaseWebConfig
    {
        protected void configure(HttpSecurity http) {
            CustomAccessDeniedHandler accessDeniedHandler = new CustomAccessDeniedHandler();
            // http.exceptionHandling().accessDeniedHandler(accessDeniedHandler);
        }

        static class CustomAccessDeniedHandler implements AccessDeniedHandler
        {
            public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException)
                    throws IOException, ServletException
            {}
        }
    }
}