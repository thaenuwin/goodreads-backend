package com.goodreadsbackend.api.util.search.util;

public abstract class ResultItemConverter<I, O> {

    public abstract O from(I i);
}
