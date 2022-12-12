package com.goodreadsbackend.api.controller;


import com.goodreadsbackend.api.domain.UserLoginResponse;
import com.goodreadsbackend.api.opr.PerformLogin;
import com.goodreadsbackend.api.util.CommonLogger;
import com.goodreadsbackend.api.util.JsonUtil;
import com.goodreadsbackend.api.util.ResponseMessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 *
 * @author thaenuwin
 */

@RestController
public class LoginController {

    @Autowired
    private PerformLogin performLogin;

    @PostMapping(path = "/login")
    public ResponseEntity<?> perform(@RequestBody String body) {

        try {
            Map<?, ?> payload = JsonUtil.fromJsonString(body, Map.class);
            String username = (String) payload.get("username");
            String password = (String) payload.get("password");

            UserLoginResponse result = performLogin.performUserLogin(username, password);
            if (result != null && result.getHttpStatusCode() != null) {
                return ResponseEntity.status(result.getHttpStatusCode()).body(result.getBody());
            }

            throw new RuntimeException("Invalid response!");

        } catch (Exception ex) {
            CommonLogger.log(LoginController.class, ex);
            return ResponseEntity.badRequest().body(ResponseMessageUtil.createFailResponseMessage("Error occurred while processing request"));
        }


    }
}