package com.goodreadsbackend.api.controller;

import com.goodreadsbackend.api.opr.UserCreation;
import com.goodreadsbackend.api.util.ResponseMessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CreateUserController {

    @Autowired
    private UserCreation userCreation;

    @PostMapping(path = "/create-user")
    public ResponseEntity<?> perform(@RequestHeader(value = "Authorization") String authorization,
                                     @RequestBody UserCreation.UserCreationCmd cmd) {
       UserCreation.CreateUserResponse statusCode= userCreation.userCreate(cmd);
        if(UserCreation.CreateUserResponse.SUCCESS.equals(statusCode)){
            return ResponseEntity.ok(ResponseMessageUtil.createSuccessResponse());
        }
        return ResponseEntity.badRequest().body(ResponseMessageUtil.createFailResponseMessage(statusCode.toString()));

    }
}
