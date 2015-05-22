/*
 * GeekSaga Class Infomation Library v0.0.1
 * 
 * http://geeksaga.com/
 * 
 * Copyright 2014 GeekSaga Foundation, Inc. and other contributors
 * 
 * Released under the MIT license http://geeksaga.com/license
 */
package com.geeksaga.forest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.geeksaga.forest.enums.code.ROLE;
import com.geeksaga.forest.service.CustomAuthenticationProvider;
import com.geeksaga.forest.service.CustomUserDetailService;
import com.geeksaga.forest.service.LoginFailureHandler;
import com.geeksaga.forest.service.LoginSuccessHandler;

/**
 * @author geeksaga
 * @version 0.1
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
    @Autowired
    private CustomUserDetailService userDetailsService;

    @Autowired
    private CustomAuthenticationProvider userDetailsProvider;

    @Autowired
    private LoginSuccessHandler successHandler;

    @Autowired
    private LoginFailureHandler failureHandler;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception
    {
        auth.authenticationProvider(userDetailsProvider);
    }

    // @Autowired
    protected void registerGlobalAuthentication(AuthenticationManagerBuilder auth) throws Exception
    {
        auth.inMemoryAuthentication().withUser("user").password("password").roles(ROLE.USER.toString());
    }

    protected void configure(HttpSecurity http) throws Exception
    {
        http.csrf();

        http.authorizeRequests().antMatchers("/", "/resources/**", "/signup", "/about").permitAll();
        http.authorizeRequests().antMatchers("/admin/**").hasRole(ROLE.ADMIN.toString());
        http.authorizeRequests().anyRequest().authenticated();
        http.formLogin().loginPage("/login").successHandler(successHandler).failureHandler(failureHandler).permitAll().and().logout()
                .permitAll();
        // .exceptionHandling()
        // .authenticationEntryPoint(authenticationEntryPoint)
        // .accessDeniedHandler(accessDeniedHandler)
    }
}