package com.goodreadsbackend.api.opr;

import lombok.Data;

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
        private String rlsDate;
    }
    public enum CreateBookResponse {
        ERROR_DUPLICATE_BOOK_FOUND,
        ERROR_BOOK_IMG_NULL,
        SUCCESS
    }
}
