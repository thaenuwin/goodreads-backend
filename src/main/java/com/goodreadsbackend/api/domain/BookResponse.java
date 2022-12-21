package com.goodreadsbackend.api.domain;

import com.goodreadsbackend.api.persistence.BookRepo;
import com.goodreadsbackend.api.persistence.entity.Book;
import com.goodreadsbackend.api.util.DateUtil;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class BookResponse {

    private String bookId;
    private String price;
    private String bookTitle;
    private String bookImg;
    private String rlsDate;
    private String summarry;
    public static BookResponse fromEntityToQueryItem(Book bookentity) {
        return BookResponse.builder()
                .bookId(bookentity.getBookId())
                .price(String.valueOf(bookentity.getBookPrice()))
                .bookTitle(bookentity.getBookTitle())
                .bookImg(bookentity.getBookImg().toString())
                .rlsDate(DateUtil.format(bookentity.getRlsDate()))
                .summarry(bookentity.getSummary())
                .build();
    }
}
