/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.checklistmanagment.controllers;

import com.checklistmanagment.database.Table;
import java.util.logging.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
/**
 *
 * @author Benjamin
 */
@RestController
@RequestMapping("/admin")
public class AdminControl extends AccountController {
    
   
    private static final Logger LOGGER = Logger.getLogger(AdminControl.class.getName());
    @RequestMapping(value="/test",method=GET)
    public String test(){
        return "<html>\n" +
"    <head>\n" +
"        <title>Worked</title>\n" +
"        <meta charset=\"UTF-8\">\n" +
"        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
"    </head>\n" +
"    <body>\n" +
"        <div>ya</div>\n" +
"    </body>\n" +
"</html>\n" +
"";
    }
    public String testtwo(){
        return "<html>\n" +
"    <head>\n" +
"        <title>Worked</title>\n" +
"        <meta charset=\"UTF-8\">\n" +
"        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
"    </head>\n" +
"    <body>\n" +
"        <div>ya ya</div>\n" +
"    </body>\n" +
"</html>\n" +
"";
    }
    
   // @RequestMapping(method =GET,value = "/account")
    Table[] getAccountData() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    void updataData() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
