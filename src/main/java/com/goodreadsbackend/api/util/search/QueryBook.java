package com.goodreadsbackend.api.util.search;

import com.goodreadsbackend.api.domain.BookResponse;
import com.goodreadsbackend.api.util.search.comp.QueryParam;
import com.goodreadsbackend.api.util.search.comp.QueryResultPage;

public interface QueryBook {

    QueryResultPage<BookResponse> query(QueryParam queryParam);
}
