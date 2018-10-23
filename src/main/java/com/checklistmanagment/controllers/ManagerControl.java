/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.checklistmanagment.controllers;

import com.checklistmanagment.database.Table;
import java.util.logging.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 *
 * @author Benjamin
 */

@RestController
@RequestMapping(value = "/manager")
public class ManagerControl extends AccountController{

    private static final Logger LOGGER = Logger.getLogger(ManagerControl.class.getName());
    
    @RequestMapping(value = "/test")
    public String test(){
        return "yup";
    }
    @Override
    Table[] getAccountData() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    void updataData() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
