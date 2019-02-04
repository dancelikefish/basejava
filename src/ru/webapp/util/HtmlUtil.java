package ru.webapp.util;

import ru.webapp.model.Organization;

import java.time.format.DateTimeFormatter;

public class HtmlUtil {

    public static String formatDates(Organization.Position position) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM/yyyy");
        return position.getStartDate().format(dateTimeFormatter) + " - " + position.getFinishDate().format(dateTimeFormatter);
    }
}
