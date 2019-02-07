package ru.webapp.util;

import ru.webapp.model.Organization;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class HtmlUtil {
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM/yyyy");

    public static String formatDates(Organization.Position position) {
        if (position.getStartDate() != null && position.getFinishDate() != null) {
            return position.getStartDate().format(dateTimeFormatter) + " - " + position.getFinishDate().format(dateTimeFormatter);
        }
        return "";
    }

    public static String formatDate(LocalDate date) {
        if (date != null) {
            return date.format(dateTimeFormatter);
        }
        return "";
    }

    public static boolean isEmpty(String value) {
        return value == null || value.trim().length() == 0;
    }
}
