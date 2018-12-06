package ru.webapp.model;

import ru.webapp.util.DateUtil;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Objects;

import static ru.webapp.util.DateUtil.NOW;

public class Organization implements Section, Serializable {
    private static final long serialVersionUID = 1L;
    private final Link homePage;
    private final List<Position> positions;

    public Organization(String name, String url, List<Position> positions) {
        this.homePage = new Link(name, url);
        this.positions = positions;
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
        return homePage.getName() + " " + homePage.getUrl() + "\n" + positions.toString();
    }

    public static class Position implements Serializable {
        private static final long serialVersionUID = 1L;
        private final String title;
        private final String description;
        private final LocalDate startDate;
        private final LocalDate finishDate;

        public Position(int startYear, Month startMonth, String title, String description) {
            this(title, description, DateUtil.of(startYear, startMonth), NOW);
        }

        public Position(int startYear, Month startMonth, int endYear, Month endMonth, String title, String description) {
            this(title, description, DateUtil.of(startYear, startMonth), DateUtil.of(endYear, endMonth));
        }

        public Position(String title, String description, LocalDate startDate, LocalDate finishDate) {
            Objects.requireNonNull(startDate, "startDate mustn't be null");
            Objects.requireNonNull(finishDate, "finishDate mustn't be null");
            Objects.requireNonNull(title, "title mustn't be null");
            this.title = title;
            this.description = description;
            this.startDate = startDate;
            this.finishDate = finishDate;
        }

        @Override
        public String toString() {
            return title + "\n" + description + "\n" + startDate + " " + finishDate;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Position)) return false;
            Position that = (Position) o;
            return Objects.equals(title, that.title) &&
                    Objects.equals(description, that.description) &&
                    Objects.equals(startDate, that.startDate) &&
                    Objects.equals(finishDate, that.finishDate);
        }

        @Override
        public int hashCode() {
            return Objects.hash(title, description, startDate, finishDate);
        }
    }
}
