package com.goodreadsbackend.api.persistence;

import com.goodreadsbackend.api.persistence.entity.Book;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface BookRepo extends PagingAndSortingRepository<Book,String> {


    Book findByBookTitle(String bookTitle);
}
