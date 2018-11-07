/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.checklistmanagment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

/**
 *
 * @author Benjamin
 */

@ComponentScan("com.checklistmanagment")
@SpringBootApplication
public class Application extends SpringBootServletInitializer {
//
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application){
        return application.sources(Application.class);
    }
    public static void main(String[] args){
        SpringApplication.run(Application.class, args);
    }
}
/**
public class Application{
    public static void main(String[] args){
        SpringApplication.run(Application.class, args);
    }
}
 **/