/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.checklistmanagment.database.controller.customcalls;

import com.checklistmanagment.database.entity.Userposition;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author Benjamin
 */
public interface userpositionRepositoryCustom extends JpaRepository<Userposition, Integer>{
    public Userposition findByUsername(String user_name);
    public List<Userposition> findByManagername(String manager_name);
    public Userposition findByManagernameAndPostionId(String manager_name, int position_id);
}
