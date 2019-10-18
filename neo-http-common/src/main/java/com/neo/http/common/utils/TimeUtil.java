package com.neo.http.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * @Author: cp.Chen
 * @since:
 * @date: 2019-10-09 15:07
 */
public class TimeUtil {

    private static String formatGMT = "EEE, d MMM yyyy HH:mm:ss 'GMT'";

    /**
     * Date 转 GMT 时间字符串
     *
     * @param d
     * @return
     */
    public static String date2gmt (Date d) {
        SimpleDateFormat sdf = new SimpleDateFormat(formatGMT);
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
        return sdf.format(d);
    }

    /**
     * GMT 时间字符串 转 Date
     *
     * @param s
     * @return
     * @throws ParseException
     */
    public static Date gmt2date (String s) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(formatGMT);
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
        return sdf.parse(s);
    }

    /**
     * 求时间差(毫秒)
     *
     * @param start 减数
     * @param end 被减数
     * @return
     */
    public static long getDifference(Date start, Date end) {
        Calendar cStart = Calendar.getInstance();
        cStart.setTime(start);
        long startL = cStart.getTimeInMillis();

        Calendar cEnd = Calendar.getInstance();
        cEnd.setTime(end);
        long endL = cEnd.getTimeInMillis();

        return startL - endL;
    }

}
