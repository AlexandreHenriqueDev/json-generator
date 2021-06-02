package br.com.dev.jsongenerator.utils;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.Random;

public class RandomUtils {

    private RandomUtils() {
    }

    public static Boolean generateRandomBoolean() {
        return new Random().nextBoolean();
    }

    public static Long generateRandomLong() {
        return new Random().nextLong();
    }

    public static Double generateRandomDouble() {
        return new Random().nextDouble();
    }

    public static Integer generateRandomInteger(Integer max) {
        return new Random().nextInt(max);
    }

    public static String generateRandomString(Integer length) {
        return RandomStringUtils.randomAlphabetic(length);
    }

}
