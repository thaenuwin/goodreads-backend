package com.goodreadsbackend.api.util.search.comp;

import lombok.Data;

import java.sql.Time;

@Data
public class TimeRangeFilterCriterion extends FilterCriterion{

    private Time start;
    private Time end;
}
