package com.apiProject.util;

import java.time.LocalDate;

public class DateConstants {

    public static int getCurrentDay() {
        LocalDate currentDate = LocalDate.now();
        return currentDate.getDayOfMonth();
    }

    public static int getCurrentMonth() {
        LocalDate currentMonth = LocalDate.now();
        return currentMonth.getMonthValue();
    }

    public static int getCurrentYear() {
        LocalDate currentYear = LocalDate.now();
        return currentYear.getYear();
    }

    public static LocalDate getLocalDate() {
        return LocalDate.now();
    }
}
