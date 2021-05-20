package br.com.dev.jsongenerator.utils;

import org.junit.jupiter.api.Test;

import static br.com.dev.jsongenerator.utils.DateUtils.futureDate;
import static br.com.dev.jsongenerator.utils.DateUtils.pastDate;

public class DateUtilsTest {

    @Test
    public void testeFutureDate() {
        futureDate();
        pastDate();
    }

}
