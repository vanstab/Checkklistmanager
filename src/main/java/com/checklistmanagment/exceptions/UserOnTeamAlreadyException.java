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
public class UserOnTeamAlreadyException extends Exception {
    public UserOnTeamAlreadyException(String name, String team,String manager_name){
       super(name+" is already on " + team + ". Please contact their current manager "+ manager_name+ ", if user has changed teams");
    }
    
}
