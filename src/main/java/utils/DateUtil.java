package utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    private static SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static String dateTimeFormate(Date date){
        if(date == null) return "";
        else return sdf1.format(date);
    }
}
