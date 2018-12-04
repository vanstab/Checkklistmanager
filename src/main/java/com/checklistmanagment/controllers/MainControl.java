/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.checklistmanagment.controllers;

import org.springframework.core.Ordered;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 *
 * @author Benjamin
 */
@Controller
public class MainControl {
/*
    @RequestMapping(value = "/")
    public String root(){
        return "redirect:/index";
    }
    @RequestMapping(value = "/index")
    public String index(){
        return "index";
    }
*/
    @RequestMapping(value = "/login")
    public String login(){
        return "login";
    }
    /*
    @PostMapping(value = "/login")
    public String loginPost(){
        return "redirect:/login-error";
    }*/
    
    @RequestMapping(value = "/login-error")
    public String loginError(Model model){
        model.addAttribute("loginError", true);
        return "login";
    }
    @RequestMapping(value = "/taskPage")
    public String taskPage(){
        return "taskPage";
    }
    
    @RequestMapping(value = "/managerPage")
    public String managerPage(){
        return "managerPage";
    } 
        
    //@Override
    //public void addViewControllers(ViewControllerRegistry registry){
      //  registry.addViewController("/login").setViewName("login");
       // registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
   // }
}
