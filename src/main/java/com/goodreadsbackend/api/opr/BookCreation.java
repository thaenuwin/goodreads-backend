package com.goodreadsbackend.api.opr;

import com.goodreadsbackend.api.util.ValidDateTime;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

public interface BookCreation {
    CreateBookResponse bookCreate(BookCreationCmd cmd);

    @Data
    public class BookCreationCmd {

        @NotEmpty
        private String bookTitle;

        private byte[] bookImage;

        @NotEmpty
        private String bookPrice;

        @NotEmpty
        private String summary;

        @NotEmpty
//        @ValidDateTime
        private String rlsDate;
    }
    public enum CreateBookResponse {
        ERROR_DUPLICATE_BOOK_FOUND,
        ERROR_BOOK_IMG_NULL,
        SUCCESS
    }
}
