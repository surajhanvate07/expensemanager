package com.expensemanager.util;


import java.sql.Date;
import java.text.SimpleDateFormat;

public class DateTimeUtil {
    public static String convertDateToString(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(date);
    }
}
