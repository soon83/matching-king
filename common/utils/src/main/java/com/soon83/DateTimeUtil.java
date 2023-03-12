package com.soon83;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class DateTimeUtil {

    public static LocalDateTime ofStartTime(LocalDateTime ldt) {
        return LocalDateTime.of(ldt.toLocalDate(), LocalTime.of(0,0,0));
    }

    public static LocalDateTime ofEndTime(LocalDateTime ldt) {
        return LocalDateTime.of(ldt.toLocalDate(), LocalTime.of(23,59,59));
    }
}
