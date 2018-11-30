package ru.webapp.model;

import java.time.LocalDate;
import java.util.Objects;

public class Employment {
    private final String occupationTitle;
    private final String description;
    private final LocalDate startDate;
    private final LocalDate finishDate;

    public Employment(String occupationTitle, String description, LocalDate startDate, LocalDate finishDate) {
        Objects.requireNonNull(startDate, "startDate mustn't be null");
        Objects.requireNonNull(finishDate, "finishDate mustn't be null");
        Objects.requireNonNull(occupationTitle, "occupationTitle mustn't be null");
        this.occupationTitle = occupationTitle;
        this.description = description;
        this.startDate = startDate;
        this.finishDate = finishDate;
    }

    @Override
    public String toString() {
        return occupationTitle + "\n" + description + "\n" + startDate + " " + finishDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employment)) return false;
        Employment that = (Employment) o;
        return Objects.equals(occupationTitle, that.occupationTitle) &&
                Objects.equals(description, that.description) &&
                Objects.equals(startDate, that.startDate) &&
                Objects.equals(finishDate, that.finishDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(occupationTitle, description, startDate, finishDate);
    }
}
