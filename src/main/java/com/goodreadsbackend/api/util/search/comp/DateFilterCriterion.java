package com.goodreadsbackend.api.util.search.comp;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class DateFilterCriterion extends FilterCriterion {

    private Date date;
}
