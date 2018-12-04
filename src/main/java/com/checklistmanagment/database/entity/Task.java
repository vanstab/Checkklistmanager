/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.checklistmanagment.database.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author Benjamin
 */
@Entity
public class Task{
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int taskId;
    private String day;
    private String description; 
    private boolean completed;
    @ManyToOne
    @JoinColumn(name="position_Id")
    private Position position;

   
    public int getTaskId(){
        return taskId;
    }
    public void setTaskId(int taskId){
        this.taskId=taskId;
    }
    public String getDay(){
        return day;
    }
    public void setDay(String day){
        this.day=day;
    }
    public String getDescription(){
        return description;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public boolean getCompleted(){
        return completed;
    }
    public void setCompleted(boolean completed){
        this.completed= completed;
    }
    
    public Position getPosition(){
        return position;
    }
    public void setPosition(Position position){
        this.position=position;
    }
}
