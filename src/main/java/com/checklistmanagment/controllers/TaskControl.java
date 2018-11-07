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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
        LOGGER.log(Level.INFO,usrpos.toString());
        return taskRepository.findTaskByPosition(usrpos.getPosition());
    }
    
    @PutMapping(value="/complete")
    public void updateTask(@RequestParam(value="taskID",required=true)int taskNumber,@RequestParam(value="completed",required=true)boolean completed) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Userposition usrpos = userpositionRepository.findByUsername(auth.getName());
        Task task = taskRepository.findById(taskNumber).get();
        if(task.getPosition().getPositionId() == usrpos.getPosition().getPositionId()){
            task.setCompleted(completed);
            taskRepository.save(task);
        }
    }
    
    @PostMapping(value="/add")
    public @ResponseBody Task addTask(@RequestParam(value="Task")Task task){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Userposition usrpos = userpositionRepository.findByUsername(auth.getName());
        task.setPosition(usrpos.getPosition());
        return taskRepository.save(task);
    }
    
    @PutMapping(value="/update")
    public void updateTask(@RequestParam(value="Task")Task task){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Userposition usrpos = userpositionRepository.findByUsername(auth.getName());
        Task checkTask = taskRepository.findById(task.gettaskId()).get();
        if(checkTask.getPosition().getPositionId() == usrpos.getPosition().getPositionId()){
            taskRepository.save(task);
        }
    }
    
    @DeleteMapping(value="/remove")
    public void removeTask(@RequestParam(value="taskID",required=true)int taskNumber){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Userposition usrpos = userpositionRepository.findByUsername(auth.getName());
        Task task = taskRepository.findById(taskNumber).get();
        if(task.getPosition().getPositionId() == usrpos.getPosition().getPositionId()){
            taskRepository.delete(task);
        }
    }
}

