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
public class UnauthorizedToMakeChangeException extends Exception {
    public UnauthorizedToMakeChangeException(String manager_name){
       super( manager_name+" is not authorized to make change.");
    }
    
}
