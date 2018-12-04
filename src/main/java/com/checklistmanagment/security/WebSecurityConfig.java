/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.checklistmanagment.security;

import com.checklistmanagment.database.controller.UserRepository;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.session.jdbc.config.annotation.web.http.EnableJdbcHttpSession;

/**
 *
 * @author Benjamin
 */
@EnableWebSecurity
@EnableJdbcHttpSession
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private static final Logger LOGGER = Logger.getLogger(WebSecurityConfig.class.getName());
    @Autowired
    private FindByIndexNameSessionRepository sessionRepository;
    
    
    @Autowired
    private UserRepository userRepo;
    
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userDetailsServiceBean());         
    }
    @Override
    public UserDetailsService userDetailsServiceBean() throws Exception{
        return new JDBCUserServiceDAO(userRepo);
    }

 
    
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        
        
        http
                .authorizeRequests()            
                    .antMatchers("/account/new").permitAll()
                    .antMatchers("/css/**").permitAll()
                    .antMatchers("/js/**").permitAll()
                    .antMatchers("/images/**").permitAll()
                    .antMatchers("/tasks/**").authenticated()
                    .antMatchers("/admin/**").authenticated()
                    .antMatchers("/user/**").authenticated()
                    .antMatchers("/manage/**").authenticated()
                    .anyRequest().authenticated()
                    .and()
                .formLogin()
                    .loginPage("/login")
                    //.successHandler(new CustomAuthenticationSuccessHandler("/html/index.html"))
                    //.loginProcessingUrl("/login")
                    //.successForwardUrl("/html/index.html")
                    .permitAll()
                    .and()
                //.addFilterBefore(getFilter(), SessionRepositoryFilter.class)
                .logout()
                    .permitAll()
                    .and()
                .sessionManagement()
                    .maximumSessions(1)
                    .expiredUrl("/logout?expired");
        
    }
    
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
           
 
    
  //  @Bean
   // public UrlBasedViewResolver urlBasedViewResolver(){
     //   UrlBasedViewResolver resolver = new UrlBasedViewResolver();
      //  resolver.setSuffix(".html");
       // return resolver;
   // }
    
   // @Bean
   // public SessionRepositoryFilter getFilter(){
    //    return new SessionRepositoryFilter(repository);
    //}
    //MapSessionRepository sessionRepository(){
      //  return new ReactiveMapSessionRepository();
    //}
}
