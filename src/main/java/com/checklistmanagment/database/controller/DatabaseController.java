/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.checklistmanagment.database.controller;

import com.checklistmanagment.database.SQLItem;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.util.Pair;

/**
 *
 * @author Benjamin
 */
public class DatabaseController {
    
    private static final Logger LOGGER = Logger.getLogger(DatabaseController.class.getName());
    
    public DatabaseController(){
        LOGGER.log(Level.INFO,"Starting DB connection");
        
    }
    public String getObjectFromTable(String type, Pair<String,String>...keysValuePair){
        LOGGER.log(Level.INFO,"Getting data from "+type+", Keys: "+ Arrays.toString(keysValuePair) );
        
        return null;
    }
    public String getAllObjectsFromTable(String type,Pair<String,String>...keysValuePair){
        return null;
    }
    public int updateObject(){
        return 1;        
    }
}
