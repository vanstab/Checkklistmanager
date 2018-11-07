/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.checklistmanagment.controllers;

import com.checklistmanagment.database.entity.Task;
import com.checklistmanagment.database.entity.User;
import com.checklistmanagment.database.controller.UserRepository;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
/**
 *
 * @author Benjamin
 */

@RestController
@RequestMapping(value = "/user")
public class UserControl {
    
    private static final Logger LOGGER = Logger.getLogger(UserControl.class.getName());
    @Autowired
    private UserRepository userRepository;
    @GetMapping(value = "/test")
    public @ResponseBody Iterable<User> test(){
        return userRepository.findAll();
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
