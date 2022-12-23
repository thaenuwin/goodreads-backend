package com.goodreadsbackend.api.util.errors;

import com.goodreadsbackend.api.util.ResponseMessageUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolation;
import java.util.Set;

@ResponseStatus(value= HttpStatus.BAD_REQUEST)  // 400
public class InputValidationException extends RuntimeException {
    public InputValidationException(String description) {

        super(description);
        System.out.println(description);
    }
}
