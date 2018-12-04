/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.checklistmanagment.controllers;

import com.checklistmanagment.database.entity.Task;
import com.checklistmanagment.database.entity.Userposition;
import com.checklistmanagment.database.controller.TaskRepository;
import com.checklistmanagment.database.controller.UserpositionRepository;
import com.checklistmanagment.exceptions.UserNotFoundException;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Benjamin
 
 */
@RestController
@RequestMapping(value = "/tasks")
public class TaskControl {
    private static final Logger LOGGER = Logger.getLogger(TaskControl.class.getName());
   
    @Autowired
    private TaskRepository taskRepository;
    
    @Autowired
    private UserpositionRepository userpositionRepository;

    @RequestMapping(method=GET)
    public @ResponseBody Iterable<Task> getUserTasks(HttpSession session) throws UserNotFoundException{
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Userposition usrpos = userpositionRepository.findByUsername(auth.getName());
        if(usrpos == null)
            return null;
        LOGGER.finer("Getting user tasks for "+auth.getName() );
        return taskRepository.findTaskByPosition(usrpos.getPosition());
    }
    
    @PutMapping(value="/completed")
    public void updateTask(@RequestParam(value="taskID",required=true)int taskNumber,@RequestParam(value="completed",required=true)boolean completed) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Userposition usrpos = userpositionRepository.findByUsername(auth.getName());
        Task task = taskRepository.findById(taskNumber).get();
        if(task.getPosition().getPositionId() == usrpos.getPosition().getPositionId()){
            task.setCompleted(completed);
            taskRepository.save(task);
        }
        LOGGER.finer("Updated user tasks "+taskNumber +" for "+auth.getName() );
    }
}

