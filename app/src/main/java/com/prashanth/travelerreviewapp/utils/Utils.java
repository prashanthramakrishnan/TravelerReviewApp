package com.prashanth.travelerreviewapp.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Utils {

    public static final String REVIEW_DETAILS = "REVIEW_DETAILS";

    public static long getRandomNumber() {
        return (long) ((Math.random() * ((100000) + 1)) + 0);
    }

    public static String parseDate(String dateString) {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.getDefault());
        Date date;
        try {
            date = formatter.parse(dateString);
        } catch (ParseException e) {
            return null;
        }
        SimpleDateFormat newFormat = new SimpleDateFormat("MMMM d,yyyy", Locale.getDefault());
        return newFormat.format(date);
    }

}
