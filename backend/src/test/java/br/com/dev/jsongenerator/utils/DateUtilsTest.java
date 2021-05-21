package br.com.dev.jsongenerator.utils;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static br.com.dev.jsongenerator.utils.DateUtils.futureDate;
import static br.com.dev.jsongenerator.utils.DateUtils.pastDate;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DateUtilsTest {

    @Test
    public void testeRandomFutureDateSuccess() {
        assertTrue(new Date().before(futureDate()));
    }

    @Test
    public void testeRandomPastDateSuccess() {
        assertTrue(new Date().after(pastDate()));
    }

    @Test
    public void testeRandomFutureDateError() {
        assertFalse(new Date().after(futureDate()));
    }

    @Test
    public void testeRandomPastDateError() {
        assertFalse(new Date().before(pastDate()));
    }

}
