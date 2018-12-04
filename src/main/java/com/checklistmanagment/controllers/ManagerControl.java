/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.checklistmanagment.controllers;

import com.checklistmanagment.database.controller.PositionRepository;
import com.checklistmanagment.database.controller.TaskRepository;
import com.checklistmanagment.database.controller.UserRepository;
import com.checklistmanagment.database.entity.Userposition;
import com.checklistmanagment.database.controller.UserpositionRepository;
import com.checklistmanagment.database.entity.Position;
import com.checklistmanagment.database.entity.Task;
import com.checklistmanagment.exceptions.PositionNotFoundException;
import com.checklistmanagment.exceptions.UnauthorizedToMakeChangeException;
import com.checklistmanagment.exceptions.UserNotFoundException;
import com.checklistmanagment.exceptions.UserOnTeamAlreadyException;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    private UserRepository userRepository;
    
    @Autowired
    private TaskRepository taskRepository;
    
    @Autowired
    private PositionRepository positionRepository;
    
    @Autowired
    private UserpositionRepository userpositionRepository;
    
    @GetMapping(value="/team")
    public @ResponseBody Iterable<Userposition> getTeamPostitions(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        LOGGER.finer("Getting "+ auth.getName()+ " team list");
        return userpositionRepository.findByManagername(auth.getName());
       
    }
    @GetMapping(value="/team/tasks")
    public @ResponseBody Iterable<Task> getPositionTasks(@RequestParam(value="positionId")int position ) throws PositionNotFoundException{
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Userposition usrp = userpositionRepository.findByManagernameAndPostionId(auth.getName(),position);
        if(usrp == null) throw new PositionNotFoundException(auth.getName(),position);
        LOGGER.finer("Getting "+ auth.getName()+ " position list for position " + position);
        return  taskRepository.findTaskByPosition(usrp.getPosition());
        
    }
    @PostMapping(value="/team/tasks")
    public @ResponseBody Task addPositionTask(@RequestBody Task task) throws UnauthorizedToMakeChangeException{
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Userposition usrp = userpositionRepository.findByManagernameAndPostionId(auth.getName(),task.getPosition().getPositionId());
        if(usrp == null) throw new UnauthorizedToMakeChangeException(auth.getName());
        LOGGER.finer("Adding task for position " + task.getPosition().getPositionId());
        return taskRepository.save(task);
    }
    @DeleteMapping(value="/team/tasks")
    public boolean removePositionTask(@RequestParam(value="taskId")int taskId) throws UnauthorizedToMakeChangeException{
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Userposition usrp = userpositionRepository.findByManagernameAndPostionId(auth.getName(),taskRepository.findById(taskId).get().getPosition().getPositionId());
        if(usrp == null) throw new UnauthorizedToMakeChangeException(auth.getName());  
        LOGGER.finer("Deleting task: " + taskId);
        taskRepository.deleteById(taskId);
        return true;
    }
    @PutMapping(value="/team/tasks")
    public @ResponseBody Task updatePositionTask(@RequestBody Task task) throws UnauthorizedToMakeChangeException{
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Userposition usrp = userpositionRepository.findByManagernameAndPostionId(auth.getName(),taskRepository.findById(task.getTaskId()).get().getPosition().getPositionId());
        if(usrp == null) throw new UnauthorizedToMakeChangeException(auth.getName());
        LOGGER.finer("Updating task "+task.getTaskId()+" for position" + task.getPosition().getPositionId());
        return taskRepository.save(task);
    }
    
    //creation and removal of team positions
    /**
     * @TODO Add so that only managers can do this + that pos is under their management
     */
    @PostMapping(value="/team/position")
    public @ResponseBody Userposition addPostion(@RequestBody Userposition userposition) throws UserOnTeamAlreadyException, UserNotFoundException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Userposition usrp =userpositionRepository.findByUsername(userposition.getUser().getUsername());
        if(usrp != null) throw new UserOnTeamAlreadyException(usrp.getUser().getUsername(),usrp.getPosition().getTeam_name(),usrp.getManager().getUsername());   
        if(!userRepository.existsById(userposition.getUser().getUsername())) throw new UserNotFoundException(userposition.getUser().getUsername());
        userposition.setPosition(positionRepository.save(userposition.getPosition()));
        userposition.setManager(userRepository.findById(auth.getName()).get());
        LOGGER.finer("Adding new position to " + auth.getName() + " team.");
        userpositionRepository.save(userposition);
        return userpositionRepository.findByManagernameAndPostionId(auth.getName(),userposition.getPosition().getPositionId());
    }
    @DeleteMapping(value="/team/position")
    public boolean removePostion(@RequestParam(value="positionId")int positionId) throws UnauthorizedToMakeChangeException{
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Userposition usrp = userpositionRepository.findByManagernameAndPostionId(auth.getName(),positionId);
        if(usrp == null) throw new UnauthorizedToMakeChangeException(auth.getName());
        positionRepository.deleteById(positionId);
        LOGGER.finer("Removing position "+positionId+ " from " + auth.getName() + " team.");
        return true;
    }
    //allows the update of position name, or team member of said position
    @PutMapping(value="/team/position")
    public void updatePostion(@RequestBody Userposition userposition) throws UnauthorizedToMakeChangeException, UserOnTeamAlreadyException, UserNotFoundException{
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Userposition usrp = userpositionRepository.findByManagernameAndPostionId(auth.getName(),userposition.getPosition().getPositionId());
        /*
        Simple checks to ensure user is allowed to make changes to position and if the user 
        is already managed by someone else, also check to make sure user exsists if adding a user to postion
        */
        if(usrp == null) throw new UnauthorizedToMakeChangeException(auth.getName());
        if(!usrp.getUser().getUsername().isEmpty()){
            Userposition temp =userpositionRepository.findByUsername(userposition.getUser().getUsername());
            if(temp != null) throw new UserOnTeamAlreadyException(temp.getUser().getUsername(),temp.getPosition().getTeam_name(),temp.getManager().getUsername());   
        }
        if(!userRepository.existsById(userposition.getUser().getUsername())) throw new UserNotFoundException(userposition.getUser().getUsername());
        
        usrp.setUser(userposition.getUser());
        userpositionRepository.save(usrp);
        LOGGER.finer("Updating position "+userposition.getPosition().getPositionId()+" on " + auth.getName() + " team.");
    
    }
    
}
