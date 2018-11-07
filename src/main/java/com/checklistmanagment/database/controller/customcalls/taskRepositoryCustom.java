/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.checklistmanagment.database.controller.customcalls;

import com.checklistmanagment.database.entity.Position;
import com.checklistmanagment.database.entity.Task;
import java.util.List;

/**
 *
 * @author Benjamin
 */
public interface taskRepositoryCustom {
    List<Task> findTaskByPosition(Position position);
}
