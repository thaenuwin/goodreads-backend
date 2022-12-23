package com.goodreadsbackend.api.persistence;

import com.goodreadsbackend.api.domain.BookResponse;
import com.goodreadsbackend.api.persistence.entity.Book;
import com.goodreadsbackend.api.persistence.entity.QBook;
import com.goodreadsbackend.api.util.JsonUtil;
import com.goodreadsbackend.api.util.search.QueryBook;
import com.goodreadsbackend.api.util.search.comp.QueryParam;
import com.goodreadsbackend.api.util.search.comp.QueryResultPage;
import com.goodreadsbackend.api.util.search.util.QueryResultPageHelper;
import com.goodreadsbackend.api.util.search.util.ResultItemConverter;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;

@Log4j2
@Component
@AllArgsConstructor
public class QueryBookAdapter implements QueryBook {

    private final EntityManager em;
    private final BookRepo bookRepo;
    @Override
    public QueryResultPage<BookResponse> query(QueryParam queryParam) {
        log.info("queryParam:{}", JsonUtil.toJsonString(queryParam));


        return QueryResultPageHelper.query(queryParam, QBook.book, em, new ResultItemConverter<Book, BookResponse>() {

            @Override
            public BookResponse from(Book bookentity) {
                log.info("Item:{}",bookentity);
                return BookResponse.fromEntityToQueryItem(bookentity);
            }
        });
    }

}
