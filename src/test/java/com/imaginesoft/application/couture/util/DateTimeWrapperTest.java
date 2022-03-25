package com.imaginesoft.application.couture.util;

import com.imaginesoft.application.couture.configuration.TestApplicationConfig;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;

import java.time.*;

import static org.junit.jupiter.api.Assertions.assertAll;

@SpringJUnitWebConfig(TestApplicationConfig.class)
class DateTimeWrapperTest implements WithAssertions {

    private final static LocalDate DATE = LocalDate.of(2021, 01, 15);
    private final static LocalTime TIME = LocalTime.of(12, 20, 10);
    private final static LocalDateTime DATE_TIME = LocalDateTime.of(DATE, TIME);

    private static final String EXPECTED_DATE = "2021-01-15";
    private static final String EXPECTED_DATE_TIME = "2021-01-15T12:20:10";
    private static final String EXPECTED_TIME = "12:20:10";

    private final static String DATE_FORMAT = "YYYY-MM-dd";
    private final static String DATE_TIME_FORMAT = "YYYY-MM-dd'T'HH:mm:ss";
    private final static String TIME_FORMAT = "HH:mm:ss";

    private final static Clock FIXED_CLOCK = Clock.fixed(DATE_TIME.atZone(ZoneId.systemDefault()).toInstant(),
            ZoneId.systemDefault());

    private final DateTimeWrapper dateTimeWrapper;

    @Autowired
    public DateTimeWrapperTest(DateTimeWrapper dateTimeWrapper) {
        this.dateTimeWrapper = dateTimeWrapper;
    }

    @BeforeEach
    void setUp() {

    }

    @Test
    void givenDate_whenCallGetDate_thenReturnsDate() {
        assertAll(
                () -> assertThat(dateTimeWrapper.getCurrentDate(FIXED_CLOCK)).isEqualTo(EXPECTED_DATE),
                () -> assertThat(dateTimeWrapper.getDate(FIXED_CLOCK, DATE_FORMAT)).isEqualTo(EXPECTED_DATE)
        );
    }

    @Test
    void givenDateTime_whenCallGetDateTime_thenReturnsDateTime() {
        assertAll(
                () -> assertThat(dateTimeWrapper.getCurrentDateTime(FIXED_CLOCK)).isEqualTo(EXPECTED_DATE_TIME),
                () -> assertThat(dateTimeWrapper.getDateTime(FIXED_CLOCK, DATE_TIME_FORMAT)).isEqualTo(EXPECTED_DATE_TIME)
        );
    }

    @Test
    void givenTime_whenCallGetTime_thenReturnsTime() {
        assertAll(
                () -> assertThat(dateTimeWrapper.getCurrentTime(FIXED_CLOCK)).isEqualTo(EXPECTED_TIME),
                () -> assertThat(dateTimeWrapper.getTime(FIXED_CLOCK, TIME_FORMAT)).isEqualTo(EXPECTED_TIME)
        );
    }
}