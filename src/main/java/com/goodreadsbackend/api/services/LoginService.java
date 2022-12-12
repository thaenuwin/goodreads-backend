/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.goodreadsbackend.api.services;

import com.goodreadsbackend.api.domain.UserLoginResponse;
import com.goodreadsbackend.api.opr.PerformLogin;
import com.goodreadsbackend.api.persistence.UserDataRepo;
import com.goodreadsbackend.api.persistence.UserLoginRepo;
import com.goodreadsbackend.api.util.AccountStatus;
import com.goodreadsbackend.api.util.CommonLogger;
import com.goodreadsbackend.api.util.HttpPostUtil;
import com.goodreadsbackend.api.util.JsonUtil;
import lombok.extern.log4j.Log4j2;

import org.apache.http.NameValuePair;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author thaenuwin
 */
@Component
@Log4j2
class LoginService implements PerformLogin {

    private static final Class TAG = LoginService.class;

    @Autowired
    UserDataRepo dataRepo;

    @Value("${server.port}")
    protected String port;
    @Value("${clientName}")
    private String clientName;
    @Value("${clientSecret}")
    private String clientSecret;
    @Value("${system.user.name}")
    protected String systemUser;


    @Autowired
    private UserLoginRepo userLoginRepo;

    @Autowired
    private UserDataRepo userDataRepo;

    @Override    
    public UserLoginResponse performUserLogin(String loginName, String password) {
        
        try {
            AccountStatus accountStatus=AccountStatus.valueOf(userDataRepo.findByUserId(loginName).getStatus());
            CommonLogger.log(TAG, "Account Status: "+accountStatus);
            if(accountStatus==null){
                UserLoginResponse response=new UserLoginResponse();
                Map<String,Object> map=new HashMap<>();
                map.put("message", "Account status unknown!");
                response.setBody(map);
                response.setHttpStatusCode(400);
                return response;
            }
            if(!AccountStatus.ACTIVE.equals(accountStatus)){
                CommonLogger.log(TAG, "Account '"+ loginName+"' was "+accountStatus.getDisplayName()+".");
                UserLoginResponse response=new UserLoginResponse();
                response.setHttpStatusCode(400);
                Map<String,Object> map=new HashMap<>();
                map.put("message", "Account "+accountStatus.getDisplayName());
                response.setBody(map);
                return response;
            }
            
            UsernamePasswordCredentials credentials
                    = new UsernamePasswordCredentials(clientName, clientSecret);
            List<NameValuePair> parameters = new ArrayList<>();
            parameters.add(new BasicNameValuePair("grant_type", "password"));
            parameters.add(new BasicNameValuePair("username", loginName));
            parameters.add(new BasicNameValuePair("password", password));

            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(parameters);

            HttpPostUtil.PostResponse res =
                    HttpPostUtil.post(
                            "http://localhost:" + port + "/oauth/token", 
                            entity, credentials);

            UserLoginResponse userLoginResponse = new UserLoginResponse();

            userLoginResponse.setBody(JsonUtil.fromJsonString(res.getResponseBody(), Map.class));
            String accessToken="";

            if(userLoginResponse.getBody()!=null){
                Map<?,?> procMap=userLoginResponse.getBody();
                procMap.remove("expires_in");
                accessToken = (String) procMap.get("access_token");
            }
            userLoginResponse.setHttpStatusCode(res.getStatusCode());

            return userLoginResponse;

        } catch (UnsupportedEncodingException ex) {
            CommonLogger.log(TAG, ex);
        } catch (Exception ex) {
            CommonLogger.log(TAG, ex);
        }
        
        return null;
    }


}
