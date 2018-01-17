package com.medi.test.meditest.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    public static String dateFormat = "dd.MM.yyyy";

    public static String getDateDDMMYYYY(Date date){
        if (date == null) return  null;
        return new SimpleDateFormat(dateFormat).format(date);
    }
}
