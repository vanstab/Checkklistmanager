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
public class UsernameExsistException extends Exception {
    public UsernameExsistException(String username){
        super(username+ " already exsists!");
    }
    
}
