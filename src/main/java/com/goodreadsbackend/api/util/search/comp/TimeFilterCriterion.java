package com.goodreadsbackend.api.util.search.comp;

import lombok.Getter;
import lombok.Setter;

import java.sql.Time;

@Getter
@Setter
public class TimeFilterCriterion extends FilterCriterion {

    private Time time;
}
