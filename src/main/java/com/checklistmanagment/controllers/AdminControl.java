/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.checklistmanagment.controllers;

import java.util.logging.Logger;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
/**
 *
 * @author Benjamin
 */
@RestController
@EnableAutoConfiguration
@RequestMapping("/admin")
public class AdminControl  {
    
   
    private static final Logger LOGGER = Logger.getLogger(AdminControl.class.getName());
    @RequestMapping(value="/test",method=GET)
    public String test(){
        return "";
    }
    
    // @RequestMapping(method =GET,value = "/account")
    public void getAccountData() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void updataData() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
