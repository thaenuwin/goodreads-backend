/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.goodreadsbackend.api.persistence;

import com.goodreadsbackend.api.persistence.entity.UserData;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


/**
 *
 * @author thaenuwin
 */

@Repository
public
interface UserDataRepo extends PagingAndSortingRepository<UserData,String> {
    
    UserData findByUserId(String userId);

    UserData findByEmail(String email);
}
