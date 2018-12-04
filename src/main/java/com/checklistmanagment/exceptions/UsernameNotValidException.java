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
public class UsernameNotValidException extends Exception {
    public UsernameNotValidException(String username){
        super(username+ " is not a email address. Please enter in a valid email.");
    }
    
}
