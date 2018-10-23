/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.checklistmanagment.controllers;

import com.checklistmanagment.database.Table;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Benjamin
 */

@RequestMapping("/checklistManager")
public abstract class AccountController {
     //return a list of object from the database table
     abstract Table[] getAccountData();
     //updates an object in table
     abstract void updataData();
}
