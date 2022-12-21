package com.goodreadsbackend.api.persistence;

import com.goodreadsbackend.api.opr.BookCreation;
import com.goodreadsbackend.api.opr.UserCreation;
import com.goodreadsbackend.api.persistence.entity.Book;
import com.goodreadsbackend.api.util.DateUtil;
import com.goodreadsbackend.api.util.ValidatorUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class BookCreationImpl implements BookCreation {

    @Autowired
    private BookRepo bookRepo;
    @Override
    public CreateBookResponse bookCreate(BookCreationCmd cmd) {
        ValidatorUtil.validate(cmd);

        String bookTitle = cmd.getBookTitle();
        Book existingbook = bookRepo.findByBookTitle(bookTitle);
        if(cmd.getBookImage().length==0){
            return CreateBookResponse.ERROR_BOOK_IMG_NULL;
        }
        if (existingbook!=null) {
            return CreateBookResponse.ERROR_DUPLICATE_BOOK_FOUND;
        }
        Book createbook= Book.builder()
                .bookTitle(cmd.getBookTitle())
                .summary(cmd.getSummary())
                .rlsDate(DateUtil.formatDate(cmd.getRlsDate()))
                .bookPrice(Integer.parseInt(cmd.getBookPrice()))
                .bookImg(cmd.getBookImage()).build();
        bookRepo.save(createbook);

        return CreateBookResponse.SUCCESS;

    }
}
