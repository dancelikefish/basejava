package ru.webapp.model;

import ru.webapp.util.DateUtil;
import ru.webapp.util.LocalDateAdapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static ru.webapp.util.DateUtil.NOW;

public class Organization extends Section implements Serializable {
    private static final long serialVersionUID = 1L;
    private Link homePage;
    private List<Position> positions;
    public static final Organization EMPTY = new Organization("", "", Position.EMPTY);

    public Organization() {
    }

    public Organization(Link homePage, List<Position> positions) {
        this.homePage = homePage;
        this.positions = positions;
    }

    public Organization(String name, String url, List<Position> positions) {
        this.homePage = new Link(name, url);
        this.positions = positions;
    }

    public Organization(String name, String url, Position... positions) {
        this(new Link(name, url), Arrays.asList(positions));
    }


    public Link getHomePage() {
        return homePage;
    }

    public List<Position> getPositions() {
        return positions;
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
        return homePage.getName() + "\n" + homePage.getUrl() + "\n" + positions.toString();
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Position implements Serializable {
        private static final long serialVersionUID = 1L;
        private String title;
        private String description;
        public static final Position EMPTY = new Position();
        @XmlJavaTypeAdapter(LocalDateAdapter.class)
        private LocalDate startDate;
        @XmlJavaTypeAdapter(LocalDateAdapter.class)
        private LocalDate finishDate;

        public Position() {
        }

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

        public String getTitle() {
            return title;
        }

        public String getDescription() {
            return description;
        }

        public LocalDate getStartDate() {
            return startDate;
        }

        public LocalDate getFinishDate() {
            return finishDate;
        }

        @Override
        public String toString() {
            return title + "\n" + description + "\n" + startDate + "\n" + finishDate;
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
