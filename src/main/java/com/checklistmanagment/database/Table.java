/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.checklistmanagment.database;

import java.util.ArrayList;
import java.util.logging.Logger;
import java.util.logging.Level;


/**
 *
 * @author Benjamin
 */

public class Table  <T extends SQLItem> {
    private static final Logger LOGGER = Logger.getLogger(Table.class.getName());
    public int create(T type){
        LOGGER.log(Level.FINER,type.toString());
        return 1;
    }
    public int update(T type){
        LOGGER.log(Level.FINER,type.toString());
        return 0;
    }
    public int remove(T type){
        LOGGER.log(Level.FINER,type.toString());
        return 0;
    }
    public T get(T type){
        LOGGER.log(Level.FINER,type.toString());
        return type;
    }
    public T[] getAll(){
        
        ArrayList<T> listOfSQLObjects = new ArrayList<T>();
        for(int x = 0; x < listOfSQLObjects.size(); x++){
            LOGGER.log(Level.FINEST,listOfSQLObjects.get(x).toString());
        }
        return null;
    }
}
