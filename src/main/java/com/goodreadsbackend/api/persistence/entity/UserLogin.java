/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.goodreadsbackend.api.persistence.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 *
 * @author thaenuwin
 */
@Entity
@Table(name = "usr_login")
@Data
public class UserLogin implements Serializable {

    private static final long serialVersionUID = 1L;
    
    
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "usr_id_num")
    private String userId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "login_pwd")
    private String loginPassword;
    @Column(name = "login_enable")
    private Integer enabled;
    @Column(name = "acc_token")
    private String accessToken;

}
