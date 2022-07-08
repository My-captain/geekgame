package iai.xmu.geek.commom.utils;

import com.google.common.collect.Lists;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.IntStream;

/**
 * 日期工具类(序列化与反序列化、时间加减、时间间隔)
 *
 * @Author: iai.xmu.edu.cn

 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils {

    public static final long SCD_MILLISECONDS = 1000;
    public static final long MIN_MILLISECONDS = 60 * 1000;
    public static final long HOUR_MILLISECONDS = 60 * 60 * 1000;
    public static final long DAY_MILLISECONDS = 24 * 60 * 60 * 1000;

    public static long getIntervalDay(Date d1, Date d2) {
        return getIntervalMilliseconds(d1, d2) / DAY_MILLISECONDS;
    }

    public static long getIntervalHour(Date d1, Date d2) {
        return getIntervalMilliseconds(d1, d2) / HOUR_MILLISECONDS;
    }

    public static long getIntervalMinute(Date d1, Date d2) {
        return getIntervalMilliseconds(d1, d2) / MIN_MILLISECONDS;
    }

    public static long getIntervalSecond(Date d1, Date d2) {
        return getIntervalMilliseconds(d1, d2) / SCD_MILLISECONDS;
    }

    public static long getIntervalMilliseconds(Date d1, Date d2) {
        return d1.getTime() - d2.getTime();
    }

    public static String fromDate(Date date, String pattern) {
        return null == date ? null : new SimpleDateFormat(pattern).format(date);
    }

    public static String fromDate(String str, String fromPattern, String toPattern) {
        return fromDate(toDate(str, fromPattern), toPattern);
    }

    public static Date toDate(String str, String format) {
        try {
            return new SimpleDateFormat(format).parse(str);
        } catch (ParseException e) {
            return null;
        }
    }

    public static Date addMonth(Date date, int interval) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, interval);
        return calendar.getTime();
    }

    public static Date addDay(Date date, int interval) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, interval);
        return calendar.getTime();
    }

    public static Date addHour(Date date, int interval) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR, interval);
        return calendar.getTime();
    }

    public static Date addMinute(Date date, int interval) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, interval);
        return calendar.getTime();
    }

    public static Date addSecond(Date date, int interval) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.SECOND, interval);
        return calendar.getTime();
    }

    public static String currentTimestamp(String pattern) {
        return fromDate(new Date(), pattern);
    }

    public static List<String> betweenYears(String fromYear, String toYear) {
        Integer from = Integer.valueOf(fromYear);
        Integer to = Integer.valueOf(toYear);
        if (from >= to) {
            return Lists.newArrayList(String.valueOf(from));
        } else {
            List<String> years = Lists.newArrayList();
            IntStream.rangeClosed(0, to - from).forEach(i -> years.add(String.valueOf(from + i)));
            return years;
        }
    }
}
