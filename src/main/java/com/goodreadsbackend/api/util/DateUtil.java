/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.goodreadsbackend.api.util;

import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author zeyarlinhtike
 */
public class DateUtil {

    private static final String FORMAT_PATTERN="yyyy-MM-dd HH:mm:ss";
    private static final String DATE_FORMAT_PATTERN = "yyyy-MM-dd";
    private DateUtil(){}
 
    public static final SimpleDateFormat createDefaultDateFormatter(){
        String datePattern = dateFormatterPattern();
        return new SimpleDateFormat(datePattern);
    }
    public static final String format(Date date) {
        return createDefaultDateFormatter().format(date);
    }


    public static final String dateFormatterPattern(){
        return FORMAT_PATTERN;
    }


    public static final Date formatDate(String date) {
        Date d = null;
        try {
            if(date != null){
                d = new SimpleDateFormat(DATE_FORMAT_PATTERN).parse(date);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return d;
    }
    public static Date addDays(Date date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days);
        return cal.getTime();
    }
}
