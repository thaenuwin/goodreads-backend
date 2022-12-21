package com.goodreadsbackend.api.util.search.comp;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class IntegerRangeFilterCriteria  extends FilterCriterion {
    private Integer start;
    private Integer end;
}
