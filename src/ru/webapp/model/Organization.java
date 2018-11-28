package ru.webapp.model;

import java.time.LocalDate;
import java.util.Objects;

public class Organization implements Section {
    private final Link homePage;
    private final String occupationTitle;
    private final String description;
    private final LocalDate startDate;
    private final LocalDate finishDate;

    public Organization(String name, String url, String occupationTitle, String description, LocalDate startDate, LocalDate finishDate) {
        Objects.requireNonNull(startDate, "startDate mustn't be null");
        Objects.requireNonNull(finishDate, "finishDate mustn't be null");
        Objects.requireNonNull(occupationTitle, "occupationTitle mustn't be null");
        this.homePage = new Link(name, url);
        this.occupationTitle = occupationTitle;
        this.description = description;
        this.startDate = startDate;
        this.finishDate = finishDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Organization that = (Organization) o;

        if (!homePage.equals(that.homePage)) return false;
        if (!occupationTitle.equals(that.occupationTitle)) return false;
        if (!Objects.equals(description, that.description)) return false;
        if (!startDate.equals(that.startDate)) return false;
        return finishDate.equals(that.finishDate);
    }

    @Override
    public int hashCode() {
        int result = homePage.hashCode();
        result = 31 * result + occupationTitle.hashCode();
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + startDate.hashCode();
        result = 31 * result + finishDate.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return description + "\n" + occupationTitle + "\n" + description + "\n" + startDate + " " + finishDate;
    }
}
