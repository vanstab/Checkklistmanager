/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.checklistmanagment.exceptions;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 *
 * @author Benjamin
 */
@RestControllerAdvice
public class ExceptionHandlers {
    
    @ExceptionHandler(PositionNotFoundException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ResponseBody
    public String handlePositionNotFound(PositionNotFoundException ex){
        return ex.getMessage();
    }
}
