package com.bs.domain.utils;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 
 * @author J. Milton Chambi M.
 */
public class DateUtil {

    /**
     * A {@link String} with the pattern <b>dd/MM/yyyy</b> to use when creating a {@link java.text.SimpleDateFormat}. Example: "22/01/1982"
     */
    public static final String SDF = "dd/MM/yyyy";

    /**
     * A {@link String} with the pattern <b>dd/MM/yyyy HH:mm</b> to use when creating a {@link java.text.SimpleDateFormat}. Example: "22/01/1982 11:25"
     */
    public static final String SDF_DETAIL = "dd/MM/yyyy HH:mm";

    /**
     * A {@link String} with the pattern <b>dd MMM yyyy</b> to use when creating a {@link java.text.SimpleDateFormat}. Example: "22 Ene 1982"
     */
    public static final String SDF_DESC = "dd MMM yyyy";

    /**
     * A {@link String} with the pattern <b>yyyyMMdd</b> to use when creating a {@link java.text.SimpleDateFormat}. Example: "19820122"
     */
    public static final String SDF_INTRA = "yyyyMMdd";

    /**
     * A {@link String} with the pattern <b>yyyyMMddHHmmss</b> to use when creating a {@link java.text.SimpleDateFormat}. Example: "19820122112530"
     */
    public static final String SDF_INTRA_LONG = "yyyyMMddHHmmss";

    public static final int MAX_HOURS_IN_DAY = 23;

    public static final int MAX_MINUTES_IN_HOUR = 59;

    public static final int MAX_SECONDS_IN_MINUTE = 59;

    public static final int MAX_MILLIS_IN_SECOND = 999;

    public static final int DAYS_IN_WEEK = 7;

    public static List<String> getLast7MonthNames() {
        Integer currentMonth = getCurrentMonth();
        Integer currentYear = DateUtil.getCurrentYear();
        List<String> months = new ArrayList<>();
        for (int i = 1; i <= 7; i++) {
            currentMonth = currentMonth - 1;
            Date firstDay = DateUtil.getFirstDayOfAMonth(currentYear, currentMonth);
            int month = getMonth(firstDay);
            String monthName = getMonthName(month);
            months.add(0, monthName);
        }
        return months;
    }

    private DateUtil() {
    }

    /**
     * Returns first date of current week, but not lower than current month's first date
     *
     * @return first date of current week in current month
     */
    public static Date getFirstDayOfCurrentWeek() {
        Calendar c = Calendar.getInstance();
        int today = c.get(Calendar.DAY_OF_MONTH);

        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);

        if (c.get(Calendar.DAY_OF_MONTH) > today) {
            return getFirstDayOfCurrentMonth();
        } else {
            return c.getTime();
        }
    }

    /**
     * Returns last date of current week, but not greater than current month's last date
     *
     * @return last date of current week in current month
     */
    public static Date getLastDayOfCurrentWeek() {
        Calendar c = Calendar.getInstance();
        int today = c.get(Calendar.DAY_OF_MONTH);

        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        if (c.get(Calendar.DAY_OF_MONTH) < today) {
            return getLastDayOfCurrentMonth();
        } else {
            c.set(Calendar.HOUR_OF_DAY, 0);
            c.set(Calendar.MINUTE, 0);
            c.set(Calendar.SECOND, 0);
            c.set(Calendar.MILLISECOND, 0);
            c.add(Calendar.DATE, 1);
            c.add(Calendar.MILLISECOND, -1);
            return c.getTime();
        }
    }

    /**
     * @return first date of current month
     */
    public static Date getFirstDayOfCurrentMonth() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.DAY_OF_MONTH, 1);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        c.setFirstDayOfWeek(Calendar.MONDAY);
        return c.getTime();
    }

    /**
     * @return last date of current month
     */
    public static Date getLastDayOfCurrentMonth() {
        Date date = getFirstDayOfCurrentMonth();
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MONTH, 1);
        c.add(Calendar.MILLISECOND, -1);
        return c.getTime();
    }

    /**
     * Sets zero hour to a date. It is useful when you need to set the start of a date range.
     *
     * @param aDate any date value
     * @return aDate with time 00:00:00-000
     */
    public static Date setZeroHour (Date aDate) {
        Calendar c = Calendar.getInstance();
        c.setTime(aDate);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime();
    }

    /**
     * Sets 24 hour to a date. It is useful when you need to set the end of a date range.
     *
     * @param aDate any date value
     * @return aDate with time 23:59:59-999
     */
    public static Date set24Hour (Date aDate) {
        Calendar c = Calendar.getInstance();
        c.setTime(aDate);
        c.set(Calendar.HOUR_OF_DAY, MAX_HOURS_IN_DAY);
        c.set(Calendar.MINUTE, MAX_MINUTES_IN_HOUR);
        c.set(Calendar.SECOND, MAX_SECONDS_IN_MINUTE);
        c.set(Calendar.MILLISECOND, MAX_MILLIS_IN_SECOND);
        return c.getTime();
    }

    /**
     * @param year  running year of requested month
     * @param month requested month, 0 based
     * @return first date of given month
     */
    public static Date getFirstDayOfAMonth(int year, int month) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, 1);
        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime();
    }

    /**
     * @param year  running year of requested month
     * @param month requested month, 0 based
     * @return last date of given month
     */
    public static Date getLastDayOfAMonth(int year, int month) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        c.add(Calendar.DATE, 1);
        c.add(Calendar.MILLISECOND, -1);
        return c.getTime();
    }

    /**
     * @param date date with we will work
     * @param days number of days that we want to add
     * @return date added days
     */
    public static Date getDateAddDays(Date date, int days) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, days);
        return c.getTime();

    }

    /**
     * @param date date with we will work
     * @param hours number of hours that we want to add
     * @return date added hours
     */
    public static Date getDateAddHours(Date date, int hours) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.HOUR, hours);
        return c.getTime();
    }

    /**
     * @param date date with we will work
     * @param minutes number of minutes that we want to add
     * @return date added minutes
     */
    public static Date getDateAddMinutes(Date date, int minutes) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MINUTE, minutes);
        return c.getTime();
    }

    /**
     * @param date date with we will work
     * @param seconds number of seconds that we want to add
     * @return date added seconds
     */
    public static Date getDateAddSeconds(Date date, int seconds) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.SECOND, seconds);
        return c.getTime();
    }

    private static Calendar getCurrentDateAMonthAgo() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH,-1);
        return cal;
    }

    public static Date getFirstDayOfPreviousMonth() {
        Calendar cal = getCurrentDateAMonthAgo();
        cal.set(cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.getActualMinimum(Calendar.DAY_OF_MONTH),
                cal.getMinimum(Calendar.HOUR_OF_DAY),
                cal.getMinimum(Calendar.MINUTE),
                cal.getMinimum(Calendar.SECOND));
        return cal.getTime();
    }

    public static Date getLastDayOfPreviousMonth() {
        Calendar cal = getCurrentDateAMonthAgo();
        cal.set(cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.getActualMaximum(Calendar.DAY_OF_MONTH),
                cal.getMaximum(Calendar.HOUR_OF_DAY),
                cal.getMaximum(Calendar.MINUTE),
                cal.getMaximum(Calendar.SECOND));
        return cal.getTime();
    }

    private static Calendar getYesterday() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1);
        return calendar;
    }

    public static String getCurrentDateDetail(){
        return new SimpleDateFormat(SDF_DETAIL).format(new Date());
    }

    public static String formatDate(Date date, String format) {
        return new SimpleDateFormat(format).format(date);
    }
    
    public static Integer getCurrentYearMonth() {
        Date now = new Date();
        return Integer.parseInt(formatDate(now, "yyyyMM"));
    }
    
    public static Integer getCurrentYear() {
        Date now = new Date();
        return Integer.parseInt(formatDate(now, "yyyy"));
    }
    
    public static Integer getCurrentMonth() {
        Date now = new Date();
        return Integer.parseInt(formatDate(now, "MM"));
    }
    
    public static Integer getTodayDayOfTheWeek() {
        Calendar c = Calendar.getInstance();
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
        if (dayOfWeek == 1)
            dayOfWeek = 7;
        else
            dayOfWeek -= 1;
        return dayOfWeek;
    }
    
    public static String getMonthName(int month) {
        switch (month) {
            case 0: return "Enero";
            case 1: return "Febrero";
            case 2: return "Marzo";
            case 3: return "Abril";
            case 4: return "Mayo";
            case 5: return "Junio";
            case 6: return "Julio";
            case 7: return "Agosto";
            case 8: return "Septiembre";
            case 9: return "Octubre";
            case 10: return "Noviembre";
            case 11: return "Diciembre";
        }
        return "";
    }
    
    public static int getMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.MONTH);
    }

    public static String formatHour(int hour) {
        String cad = String.valueOf(hour);
        if (cad.length() < 3)
            return "00:" + cad;
        if (cad.length() == 3)
            return "0" + cad.substring(0, 1) + ":" + cad.substring(1, 3);
        return cad.substring(0, 2) + ":" + cad.substring(2, 4);
    }

    public static void main(String[] args) {
        Date expirationDate = new Date(1562644800000L);
        System.out.println("EXPIRATION DATE: " + expirationDate);
    }
    
}
