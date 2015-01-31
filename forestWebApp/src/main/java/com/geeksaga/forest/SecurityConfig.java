package com.geeksaga.forest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.geeksaga.forest.service.AuthorityService;
import com.geeksaga.forest.service.LoginFailureHandler;
import com.geeksaga.forest.service.LoginSuccessHandler;

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

    @Autowired
    private LoginSuccessHandler successHandler;

    @Autowired
    private LoginFailureHandler failureHandler;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception
    {
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
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
        http.formLogin().loginPage("/login").successHandler(successHandler).failureHandler(failureHandler).permitAll().and().logout()
                .permitAll();
        // http.formLogin().loginPage("/login").loginProcessingUrl("/login_post").permitAll().and().logout().permitAll();
        // .exceptionHandling()
        // .authenticationEntryPoint(authenticationEntryPoint)
        // .accessDeniedHandler(accessDeniedHandler)
    }
}