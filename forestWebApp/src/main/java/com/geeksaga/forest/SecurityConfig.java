package com.geeksaga.forest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
    @Autowired
    public void registerGlobalAuthentication(AuthenticationManagerBuilder auth) throws Exception
    {
        auth.inMemoryAuthentication().withUser("user").password("password").roles("USER");
    }

    protected void configure(HttpSecurity http) throws Exception
    {
        http.csrf();

        http.authorizeRequests().antMatchers("/", "/resources/**", "/signup", "/about").permitAll();
        http.authorizeRequests().antMatchers("/admin/**").hasRole("ADMIN");
        // http.authorizeRequests().antMatchers("/db/**").access("hasRole('ROLE_ADMIN') and hasRole('ROLE_DBA')");
        http.authorizeRequests().anyRequest().authenticated();
        http.formLogin().loginPage("/login").permitAll().and().logout().permitAll();
        // http.formLogin().permitAll().and().logout().permitAll();
    }
}