package com.geeksaga.forest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.geeksaga.forest.service.AuthorityService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
    @Autowired
    private AuthorityService userDetailsService;

    // @Autowired
    // private AuthenticationEntryPoint authenticationEntryPoint;
    //
    // @Autowired
    // private AccessDeniedHandler accessDeniedHandler;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception
    {
        auth.userDetailsService(userDetailsService);// .passwordEncoder(passwordEncoder);
    }

    @Autowired
    protected void registerGlobalAuthentication(AuthenticationManagerBuilder auth) throws Exception
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
        // http.formLogin().loginPage("/login").loginProcessingUrl("/login_post").permitAll().and().logout().permitAll();
        // .exceptionHandling()
        // .authenticationEntryPoint(authenticationEntryPoint)
        // .accessDeniedHandler(accessDeniedHandler)
    }
}