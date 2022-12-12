/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.goodreadsbackend.api.util;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author thaenuwin
 */
public class CommonLogger {
    
    
    private CommonLogger(){}
    
    
    public static final void log(Class<?> clazz,String message){
       Logger.getLogger(clazz.getName()).log(Level.INFO,message);
    }
    
    public static final void log(Class<?> clazz,Throwable t){
        Logger.getLogger(clazz.getName()).log(Level.INFO,"Error - ",t);
    }
}
