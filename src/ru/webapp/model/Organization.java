package ru.webapp.model;

import java.util.List;
import java.util.Objects;

public class Organization implements Section {
    private final Link homePage;
    private final List<Employment> employments;

    public Organization(String name, String url, List<Employment> employments) {
        this.homePage = new Link(name, url);
        this.employments = employments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Organization)) return false;
        Organization that = (Organization) o;
        return Objects.equals(homePage, that.homePage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(homePage);
    }

    @Override
    public String toString() {
        return homePage.getName() + " " + homePage.getUrl() + "\n" + employments.toString();
    }
}
