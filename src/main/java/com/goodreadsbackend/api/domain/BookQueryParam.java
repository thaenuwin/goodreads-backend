package com.goodreadsbackend.api.domain;

import com.goodreadsbackend.api.persistence.entity.Book;
import com.goodreadsbackend.api.util.search.comp.QueryParam;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookQueryParam extends QueryParam {
    private String minPrice;
    private String maxPrice;
    private String bookId;
    private String bookTitle;
    private String rlsStartDate;
    private String rlsEndDate;

}
