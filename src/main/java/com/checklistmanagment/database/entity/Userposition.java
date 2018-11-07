/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.checklistmanagment.database.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

/**
 *
 * @author Benjamin
 */
@Entity
@NamedQueries({
    @NamedQuery(name="Userposition.findByUsername", query="select usrp from Userposition usrp where usrp.user.user_name=?1"),
    @NamedQuery(name="Userposition.findByManagername", query="select usrp.user.name,usrp.user.user_name, usrp.position from Userposition usrp where usrp.manager.user_name=?1"),
    @NamedQuery(name="Userposition.findByManagernameAndPostionId", query="select usrp from Userposition usrp where usrp.manager.user_name=?1 AND usrp.position.position_Id=?2")

})
public class Userposition {
    @Id
    @GeneratedValue
    private int id;
    @OneToOne
    @JoinColumn(name="user_name")
    private User user;
    @OneToOne
    @JoinColumn(name="manager_name")
    private User manager;
    @OneToOne
    @JoinColumn(name="position_Id")
    private Position position;
    
    public User getUser(){
        return this.user;
    }
    public void setUser(User user){
        this.user = user;
    }
        
    public User getManager(){
        return this.manager;
    }
    public void setManager(User manager){
        this.manager = manager;
    }
        
    public Position getPosition(){
        return this.position;
    }
    public void setPosition(Position position){
        this.position = position;
    }
    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id=id;
    }
}
