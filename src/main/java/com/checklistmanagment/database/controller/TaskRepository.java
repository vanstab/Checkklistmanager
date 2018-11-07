/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.checklistmanagment.database.controller;
import com.checklistmanagment.database.entity.Task;
import com.checklistmanagment.database.controller.customcalls.taskRepositoryCustom;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Benjamin
 */

public interface TaskRepository extends CrudRepository<Task,Integer>,taskRepositoryCustom {
   
}
