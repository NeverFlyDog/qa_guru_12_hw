package ru.example.data;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

public record CalendarDate(String year, String month, int day) {

    public static CalendarDate now() {
        LocalDate today = LocalDate.now();
        return new CalendarDate(
                String.valueOf(today.getYear()),
                today.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH),
                today.getDayOfMonth()
        );
    }

    @Override
    public String toString() {
        return day + " " + month + " " + year;
    }
}
