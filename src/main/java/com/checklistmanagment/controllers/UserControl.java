/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.checklistmanagment.controllers;

import com.checklistmanagment.database.Table;
import com.checklistmanagment.database.Task;
import com.checklistmanagment.database.controller.DatabaseController;
import java.util.logging.Logger;
import javafx.util.Pair;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
/**
 *
 * @author Benjamin
 */

@RestController
@RequestMapping(value = "/user")
public class UserControl {

    private static final Logger LOGGER = Logger.getLogger(UserControl.class.getName());
    
    @RequestMapping(value = "/test")
    public String test(){
        return "dubble yup";
    }
    @RequestMapping(value="/account")
    public Task[] getAccountData(@RequestParam(value="username")String user) {
        //DatabaseController dbControl = new DatabaseController();
        //Pair[] keys= {new Pair(SQLUser.primaryKeyUsername,"username"),new Pair(SQLUser.primaryKeyPassword,"password")} ;
        //dbControl.getObjectFromTable(SQLUser.tableName, keys);
        return null;
    }

    public void updataData() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
