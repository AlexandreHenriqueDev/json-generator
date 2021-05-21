package br.com.dev.jsongenerator.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import static br.com.dev.jsongenerator.constants.DateConstants.*;
import static br.com.dev.jsongenerator.utils.RandomUtils.generateRandomBoolean;

public class DateUtils {

    private static Random random = new Random();
    private DateUtils() {}

    public static Date futureDate() {
        var calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int futureYear = random.nextInt(YEARS_FUTURE_INTERVAL) + year;
        int futureMonth = random.nextInt(MONTHS_FUTURE_INTERVAL - month) + month;
        int futureDay = random.nextInt(DAYS_FUTURE_INTERVAL - day) + day;

        calendar.set(futureYear, futureMonth, futureDay);

        return calendar.getTime();
    }

    public static Date pastDate() {
        var calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int pastYear = year - random.nextInt(YEARS_PAST_INTERVAL);
        int pastMonth = random.nextInt(month);
        int pastDay = random.nextInt(day);

        calendar.set(pastYear, pastMonth, pastDay);

        return calendar.getTime();
    }

    public static Date randomDate() {
        return generateRandomBoolean() ? futureDate() : pastDate();
    }

    private static Date currentDate() {
        return Date.from(Calendar.getInstance().toInstant());
    }

}
