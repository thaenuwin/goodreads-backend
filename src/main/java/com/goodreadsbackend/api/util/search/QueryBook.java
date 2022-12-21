package com.goodreadsbackend.api.util.search;

import com.goodreadsbackend.api.domain.BookQueryParam;
import com.goodreadsbackend.api.domain.BookResponse;
import com.goodreadsbackend.api.util.search.comp.QueryParam;
import com.goodreadsbackend.api.util.search.comp.QueryResultPage;

import java.util.List;

public interface QueryBook {

    QueryResultPage<BookResponse> query(QueryParam queryParam);
}
