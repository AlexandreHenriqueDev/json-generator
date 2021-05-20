package br.com.dev.jsongenerator.utils;

import br.com.dev.jsongenerator.dto.ObjectReaderDto;
import br.com.dev.jsongenerator.enums.FormatterEnum;
import br.com.dev.jsongenerator.enums.TypeEnum;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.*;

import static br.com.dev.jsongenerator.constants.ObjectsFormConstant.MARKS;
import static br.com.dev.jsongenerator.constants.ObjectsFormConstant.TWO_DOTS;
import static br.com.dev.jsongenerator.utils.CpfCnpjUtils.getRandom;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

public class ObjectFormUtils {

    private static StringBuilder sb;

    public static List<ObjectReaderDto> toListDto(List<Object> listObject) {
        List<ObjectReaderDto> list = new ArrayList<>();
        for(Object object : listObject) {
            LinkedHashMap map = ((LinkedHashMap) object);
            String property = (String) map.get("property");
            Object value = map.get("value");
            TypeEnum type = TypeEnum.valueOf((String) map.get("type"));
            Integer size = (Integer) map.get("size");
            String form = (String) map.get("formatter");
            Boolean isNull = (Boolean) map.get("isNull");
            FormatterEnum formatter = nonNull(form) ? FormatterEnum.valueOf(form) : null;
            list.add(ObjectReaderDto
                    .builder()
                    .property(property)
                    .value(value)
                    .type(type)
                    .size(size)
                    .isNull(isNull)
                    .formatter(formatter)
                    .build());
        }
        return list;
    }

    public static String readObject(String property, String value, Integer length, FormatterEnum formatter) {
        sb = new StringBuilder();
        if(nonNull(property)) {
            sb.append(MARKS).append(property).append(MARKS).append(TWO_DOTS);
        }

        String genatedValue = nonNull(formatter)
                ? getRandom(formatter)
                : generateRandomString(nonNull(length) ? length : 20);

        sb.append(MARKS).append(isNull(value) ? genatedValue : value);
        return sb.append(MARKS).toString();
    }

    public static String readObject(String property, Integer value, Integer interval) {
        sb = new StringBuilder();
        if(nonNull(property)) {
            sb.append(MARKS).append(property).append(MARKS).append(TWO_DOTS);
        }
        sb.append(isNull(value) ? generateRandomInteger(nonNull(interval) ? interval : Integer.MAX_VALUE) : value);
        return sb.toString();
    }

    public static String readObject(String property, Double value) {
        sb = new StringBuilder();
        if(nonNull(property)) {
            sb.append(MARKS).append(property).append(MARKS).append(TWO_DOTS);
        }
        sb.append(isNull(value) ? generateRandomInteger(Integer.MAX_VALUE) + generateRandomDouble() : value);
        return sb.toString();
    }

    public static String readObject(String property, Long value, FormatterEnum formatter) {
        sb = new StringBuilder();
        if(nonNull(property)) {
            sb.append(MARKS).append(property).append(MARKS).append(TWO_DOTS);
        }

        Long genatedValue = nonNull(formatter)
                ? Long.valueOf(getRandom(formatter)).longValue()
                : generateRandomLong();

        sb.append(isNull(value) ? genatedValue : value);
        return sb.toString();
    }

    public static String readObject(String property, Boolean value) {
        sb = new StringBuilder();
        if(nonNull(property)) {
            sb.append(MARKS).append(property).append(MARKS).append(TWO_DOTS);
        }
        sb.append(isNull(value) ? generateRandomBoolean() : value);
        return sb.toString();
    }

    public static String readObject(String property, Date value) {
        sb = new StringBuilder();
        if(nonNull(property)) {
            sb.append(MARKS).append(property).append(MARKS).append(TWO_DOTS);
        }
        sb.append(MARKS).append(isNull(value) ? new Date() : value);
        return sb.append(MARKS).toString();
    }

    private static Boolean generateRandomBoolean() {
        return new Random().nextBoolean();
    }

    private static Long generateRandomLong() {
        return new Random().nextLong();
    }

    private static Double generateRandomDouble() {
        return new Random().nextDouble();
    }

    private static Integer generateRandomInteger(Integer max) {
        Random random = new Random();
        return random.nextInt(max);
    }

    private static String generateRandomString(Integer length) {
        String generatedString = RandomStringUtils.randomAlphabetic(length);
        return generatedString;
    }

}
