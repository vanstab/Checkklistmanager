/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.checklistmanagment.controllers;

import com.checklistmanagment.database.entity.User;
import com.checklistmanagment.database.controller.UserRepository;
import com.checklistmanagment.exceptions.EmptyFieldsException;
import com.checklistmanagment.exceptions.UsernameExsistException;
import com.checklistmanagment.exceptions.UsernameNotValidException;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.apache.commons.validator.EmailValidator;
/**
 *
 * @author Benjamin
 */

@RestController
@RequestMapping(value = "/account")
public class UserControl {
    
    private static final Logger LOGGER = Logger.getLogger(UserControl.class.getName());
    @Autowired
    private UserRepository userRepository;
    @PostMapping(value = "/new")
    public boolean newUser(User user) throws UsernameNotValidException, UsernameExsistException, EmptyFieldsException{
        if(!EmailValidator.getInstance().isValid(user.getUsername()))throw new UsernameNotValidException(user.getUsername());
        if(userRepository.findById(user.getUsername()).isPresent())  throw new UsernameExsistException(user.getUsername());
        if(user.getPassword().isEmpty() || user.getName().isEmpty()) throw new EmptyFieldsException();
        
        BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
        user.setPassword(bcrypt.encode(user.getPassword()));
        userRepository.save(user);
        LOGGER.info("New user added: " + user.getUsername());
        return true;
    }

    public void updataData() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
