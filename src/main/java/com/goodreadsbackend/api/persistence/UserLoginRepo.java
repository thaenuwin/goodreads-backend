/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.goodreadsbackend.api.persistence;


import com.goodreadsbackend.api.persistence.entity.UserLogin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author thaenuwin
 */

@Repository
public interface UserLoginRepo extends JpaRepository<UserLogin, String>{

    UserLogin findByUserId(String userId);

    @Transactional
    @Modifying
    @Query("update UserLogin u set u.accessToken = ?1 where u.userId = ?2")
    void updateByUserId(String accessToken, String id);
}
