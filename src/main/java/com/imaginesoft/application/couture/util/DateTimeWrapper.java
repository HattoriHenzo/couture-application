package com.imaginesoft.application.couture.util;

import org.springframework.stereotype.Component;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Component
public class DateTimeWrapper {

    public DateTimeWrapper() {

    }

    public String getCurrentDateTime(Clock clock) {
        return getDateTime(clock).format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }

    public String getCurrentDate(Clock clock) {
        return getDate(clock).format(DateTimeFormatter.ISO_LOCAL_DATE);
    }

    public String getCurrentTime(Clock clock) {
        return getTime(clock).format(DateTimeFormatter.ISO_LOCAL_TIME);
    }

    public Long getCurrentTimeStamp(Clock clock) {
        return clock.millis();
    }

    public String getDateTime(Clock clock, String format) {
        return LocalDateTime.now(clock).format(DateTimeFormatter.ofPattern(format));
    }

    public String getDate(Clock clock, String format) {
        return LocalDate.now(clock).format(DateTimeFormatter.ofPattern(format));
    }

    public String getTime(Clock clock, String format) {
        return LocalTime.now(clock).format(DateTimeFormatter.ofPattern(format));
    }

    private LocalDateTime getDateTime(Clock clock) {
        return LocalDateTime.now(clock);
    }

    private LocalDate getDate(Clock clock) {
        return LocalDate.now(clock);
    }

    private LocalTime getTime(Clock clock) {
        return LocalTime.now(clock);
    }

}
