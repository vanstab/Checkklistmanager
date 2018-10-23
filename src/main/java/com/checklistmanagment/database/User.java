/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.checklistmanagment.database;

/**
 *
 * @author Benjamin
 */
public class User extends SQLItem {

    public static final String tableName="User";
    public static final String primaryKeyUsername="username";
    public static final String primaryKeyPassword="password";
    public void setValues(){
        
    };
    public String toString(){
        return "";
    }
}
