/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.checklistmanagment.database.entity;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import org.springframework.security.core.GrantedAuthority;
/**
 *
 * @author Benjamin
 */
@Entity
public class Role implements GrantedAuthority{
    
    @Id
    private String role_name;
    
    @ManyToMany(mappedBy = "authorities")
    private Set<User> users;
    public String getRolename(){
        return this.role_name;
    }
    
    public void setRolename(String role_name){
        this.role_name = role_name;
    }

    @Override
    public String getAuthority() {
        return role_name;
    }
}
