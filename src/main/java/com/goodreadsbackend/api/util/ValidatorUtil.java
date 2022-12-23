/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.goodreadsbackend.api.util;

import com.goodreadsbackend.api.util.errors.InputValidationException;
import javassist.tools.web.BadHttpRequest;

import javax.validation.*;
import java.util.Set;

/**
 *
 * @author zlhso
 * @param <T>
 */
public class ValidatorUtil {
    
    private ValidatorUtil(){}

    public static void validate(Object t){
        ValidatorFactory factory= Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Object>> violations = validator.validate(t);
        if(!violations.isEmpty()){
            throw new ConstraintViolationException(violations);
        }
    }
}
