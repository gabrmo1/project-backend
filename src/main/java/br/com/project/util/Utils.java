package br.com.project.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {

    public static Date convertStringToDate(String str) throws ParseException {
        return str.length() > 10 ? createDateWithHours(str) : new SimpleDateFormat("yyyy-MM-dd").parse(str);
    }

    private static Date createDateWithHours(String str) throws ParseException {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(str);
    }

}
