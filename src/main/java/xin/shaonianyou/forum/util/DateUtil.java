package xin.shaonianyou.forum.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

    /**
     * 获取本周的第一天
     *
     * @return String
     **/
    public String getWeekStart() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.WEEK_OF_MONTH, 0);
        cal.set(Calendar.DAY_OF_WEEK, 2);
        Date time = cal.getTime();
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(time);
    }

    /**
     * 获取本周的最后一天
     *
     * @return String
     **/
    public static String getWeekEnd() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_WEEK, cal.getActualMaximum(Calendar.DAY_OF_WEEK));
        cal.add(Calendar.DAY_OF_WEEK, 1);
        Date time = cal.getTime();
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(time);
    }

    /**
     * 获取今天
     *
     * @return String
     */
    public static String getToday() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }

    public static  String parse(Date date){
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
    }

}
