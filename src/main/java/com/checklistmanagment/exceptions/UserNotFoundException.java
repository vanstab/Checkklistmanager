/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.checklistmanagment.exceptions;

/**
 *
 * @author Benjamin
 */
public class UserNotFoundException extends Exception {
    public UserNotFoundException(String username){
        super("Failed to find " +username+ ".");
    }
    
}
