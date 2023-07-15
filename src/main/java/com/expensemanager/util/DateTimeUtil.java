package com.expensemanager.util;


import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateTimeUtil {
    public static String convertDateToString(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(date);
    }

    public static Date convertStringToDate(String dateString) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        java.util.Date utilDate = dateFormat.parse(dateString);

        return new Date(utilDate.getTime());
    }
}
