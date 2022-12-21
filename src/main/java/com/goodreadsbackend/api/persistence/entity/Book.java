package com.goodreadsbackend.api.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.codehaus.jackson.annotate.JsonSubTypes;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;


@Table(name = "book")
@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Book {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "book_id_num")
    private String bookId;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "book_title")
    private String bookTitle;

    @Lob @Basic(fetch = FetchType.LAZY)
    @Column(name = "book_img",columnDefinition = "LONGBLOB")
    private byte[] bookImg;

    @Column(name = "book_price")
    private Integer bookPrice;

    @Column(name = "summary")
    @Size(min = 1, max = 5000)
    private String summary;

    @Column(name = "rls_dttm")
    private Date rlsDate;

}

