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
public class PositionNotFoundException extends Exception {
    public PositionNotFoundException(String manager_name,int position_id){
       super("The position id: " + position_id +" did not exsist under manager: " + manager_name+".");
    }
    
}
