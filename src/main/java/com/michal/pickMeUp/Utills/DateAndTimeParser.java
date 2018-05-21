package com.michal.pickMeUp.Utills;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Michał on 20.08.2017.
 */
public class DateAndTimeParser {

    public Date parse(String date, String time){
        Date parsedDate= new Date();
        try {
            String unparsedDate=date+" "+time;
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
            parsedDate=format.parse(unparsedDate);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return parsedDate;

    }
}