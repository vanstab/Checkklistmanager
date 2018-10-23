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
public abstract class SQLItem {
   String tableName="Invalid_Table_Name";
   abstract public void setValues();
   abstract public String toString();
}
