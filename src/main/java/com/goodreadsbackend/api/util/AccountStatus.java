/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.goodreadsbackend.api.util;

/**
 *
 * @author zeyarlinhtike
 */
public enum AccountStatus {


    ACTIVE("ACTIVE"),

    LOCKED("LOCKED");
    
    
    
    AccountStatus(String displayName){
        this.displayName=displayName;
    }
    private String displayName;

    /**
     * @return the displayName
     */
    public String getDisplayName() {
        return displayName;
    }
}
