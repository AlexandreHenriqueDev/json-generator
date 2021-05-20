package br.com.dev.jsongenerator.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class DateUtils {

    private static Random random = new Random();
    private DateUtils() {}

    public static Date futureDate() {
        var calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int futureYear = random.nextInt(10) + year;
        int futureMonth = random.nextInt(12 - month) + month;
        int futureDay = random.nextInt(30 - day) + day;

        calendar.set(futureYear, futureMonth, futureDay);

        return calendar.getTime();
    }

    public static Date pastDate() {
        var calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int pastYear = year - random.nextInt(2);
        int pastMonth = random.nextInt(month);
        int pastDay = random.nextInt(day);

        calendar.set(pastYear, pastMonth, pastDay);

        return calendar.getTime();
    }

    public static Date randomDate() {
        return new Date();
    }

    private static Date currentDate() {
        return Date.from(Calendar.getInstance().toInstant());
    }



}
