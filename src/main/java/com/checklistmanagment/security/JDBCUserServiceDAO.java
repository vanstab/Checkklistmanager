/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.checklistmanagment.security;

import com.checklistmanagment.database.controller.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 *
 * @author Benjamin
 */
public class JDBCUserServiceDAO implements UserDetailsService{

    private UserRepository userRepo;
    public JDBCUserServiceDAO(UserRepository userRepo){
        this.userRepo=userRepo;
    }
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findById(username).get();
    }
    
}
