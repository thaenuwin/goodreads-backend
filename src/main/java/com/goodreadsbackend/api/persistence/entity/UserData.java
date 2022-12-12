/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.goodreadsbackend.api.persistence.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Date;


/**
 *
 * @author thaenuwin
 */
@Entity
@Table(name = "usr_data")
@Data
public class UserData implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "usr_id_num")
    private String userId;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "disp_nam_txt")
    private String displayName;    
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 255)
    @Column(name = "email_addr")
    private String email;
    
    @Size(max = 255)
    @Column(name = "tel_num")
    private String phoneNumber;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "crt_dttm")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "upd_dttm")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate;

    @Size(max = 255)
    @Column(name = "acct_st_txt")
    private String status;


    
}
