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

/**
 *
 * @author Benjamin
 */
@Entity
public class Position {
   
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int position_Id;
    private String position_name;
    private String team_name;

    public String getPosition_name() {
        return position_name;
    }

    public void setPosition_name(String position_name) {
        this.position_name=position_name;
    }

    public String getTeam_name() {
        return team_name;
    }

    public void setTeam_name(String team_name) {
        this.team_name=team_name;
    }
    public int getPositionId() {
       return position_Id;
    }
    public void setPositionId(int positionId) {
       this.position_Id= positionId;
    }
    
}
