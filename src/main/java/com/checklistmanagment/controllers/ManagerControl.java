/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.checklistmanagment.controllers;

import com.checklistmanagment.database.controller.TaskRepository;
import com.checklistmanagment.database.entity.Userposition;
import com.checklistmanagment.database.controller.UserpositionRepository;
import com.checklistmanagment.database.entity.Position;
import com.checklistmanagment.database.entity.Task;
import com.checklistmanagment.exceptions.PositionNotFoundException;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
/**
 *
 * @author Benjamin
 */

@RestController
@RequestMapping(value = "/manager")
public class ManagerControl {

    private static final Logger LOGGER = Logger.getLogger(ManagerControl.class.getName());
    
    @Autowired
    private TaskRepository taskRepository;
    
    
    @Autowired
    private UserpositionRepository userpositionRepository;
    
    @GetMapping(value="/team")
    public @ResponseBody Iterable<Userposition> getTeamPostition(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return userpositionRepository.findByManagername(auth.getName());
       
    }
    @GetMapping(value="/team/tasks")
    public @ResponseBody Iterable<Task> getPositionTasks(@RequestParam(value="position")Position position ) throws PositionNotFoundException{
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Userposition usrp = userpositionRepository.findByManagernameAndPostionId(auth.getName(),position.getPositionId());
        if(usrp == null) throw new PositionNotFoundException(auth.getName(),position.getPositionId());
        return  taskRepository.findTaskByPosition(usrp.getPosition());
        
    }
    //create/remove/update task for a position
    public void addPositionTask(){
    }
    public void removePositionTask(){
    }
    public void updatePositionTask(){
    }
    
    //creation and removal of team positions
    public void addPostion(){
    }
    public void removePostion(){
    }
    //allows the update of position name, or team member of said position
    public void updatePostion(){
    }
    
}
