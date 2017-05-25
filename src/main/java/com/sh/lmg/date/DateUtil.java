package com.sh.lmg.date;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by liaomengge on 17/5/25.
 */
public final class DateUtil {

    public static final long MILLISECONDS_SECOND = 1000;

    public final static String yyyy_MM_dd_HH_mm_ss = "yyyy-MM-dd HH:mm:ss";
    public final static String yyyy_MM_dd = "yyyy-MM-dd";
    public final static String yyyyMMdd = "yyyyMMdd";

    private DateUtil() {
    }

    public static String getNowDate2String() {
        return DateFormatUtils.format(new Date(), yyyy_MM_dd_HH_mm_ss);
    }

    public static String getNowDate2String(String format) {
        return DateFormatUtils.format(new Date(), format);
    }

    public static String getDate2String(Date date) {
        return getDate2String(date, yyyy_MM_dd_HH_mm_ss);
    }

    public static String getDate2String(Date date, String format) {
        if (date == null) {
            return "";
        }
        return DateFormatUtils.format(date, format);
    }

    public static Date getString2Date(String date) {
        return getString2Date(date, yyyy_MM_dd_HH_mm_ss);
    }

    public static Date getString2Date(String date, String format) {
        String[] date_format = {format};
        try {
            return DateUtils.parseDate(date, date_format);
        } catch (ParseException e) {
            return null;
        }
    }

    public static long getPhpTime() {
        return new Date().getTime() / MILLISECONDS_SECOND;
    }

    public static long getPhpTime(Date date) {
        if (date == null) {
            return 0;
        }
        return date.getTime() / MILLISECONDS_SECOND;
    }

    public static long getTime() {
        return new Date().getTime();
    }

    /**
     * 获取今天起始时间字符串
     *
     * @return
     */
    public static String getTodayBegin2String() {
        return DateFormatUtils.format(getTodayBegin(), yyyy_MM_dd_HH_mm_ss);
    }

    /**
     * 获取今天结束时间字符串
     *
     * @return
     */
    public static String getTodayEnd2String() {
        return DateFormatUtils.format(getTodayEnd(), yyyy_MM_dd_HH_mm_ss);
    }

    /**
     * 获取今天的开始时刻
     *
     * @return
     */
    public static Date getTodayBegin() {
        return getBegin4Date(new Date());
    }


    /**
     * 获取今天的结束时刻
     *
     * @return
     */
    public static Date getTodayEnd() {
        return getEnd4Date(new Date());
    }

    public static Date getBegin4Date(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        return calendar.getTime();
    }

    public static Date getEnd4Date(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), 23, 59, 59);
        return calendar.getTime();
    }

    /**
     * 返回今天是星期几（按西方习惯，星期天返回0）
     *
     * @return
     */
    public static int getDayOfWeek() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        calendar.setTime(new Date());
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        return day - 1;
    }

    /**
     * 按中国习惯，返回今天是星期几(星期天返回7)
     *
     * @return
     */
    public static int getDayOfWeekCN() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        calendar.setTime(new Date());
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        if (day == 1) {//外国，星期天为每个星期的第1天
            return 7;
        }
        return day - 1;
    }

    /**
     * 得到指定日期是几号
     *
     * @param date
     * @return
     */
    public static int getDayOfMonth(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 得到今天是几号
     *
     * @return
     */
    public static int getDayOfMonth() {
        return Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 得到指定日期的月份
     *
     * @param date
     * @return
     */
    public static int getMonth(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.MONTH) + 1;
    }

    public static int getHour(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.HOUR_OF_DAY);
    }

    public static int getMinute(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.MINUTE);
    }

    public static String getStringMonth(Date date) {
        return DateFormatUtils.format(date, "MM");
    }

    public static String getStringDate(Date date) {
        return DateFormatUtils.format(date, "dd");
    }

    public static String getStringHour(Date date) {
        return DateFormatUtils.format(date, "HH");
    }

    public static String getStringMinute(Date date) {
        return DateFormatUtils.format(date, "mm");
    }
}
