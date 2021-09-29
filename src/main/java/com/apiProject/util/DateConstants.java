package com.apiProject.util;

import java.time.LocalDate;

public class DateConstants {

    public static int getCurrentDay() {
        LocalDate currentDate = LocalDate.now();
        return currentDate.getDayOfMonth();
    }

    public static LocalDate getLocalDate() {
        return LocalDate.now();
    }
}
