package ru.webapp.model;

import java.time.LocalDate;
import java.util.Objects;

public class Organisation implements Section {
    private String occupationPlace;
    private String occupationTitle;
    private String duties;
    private LocalDate start;
    private LocalDate finish;

    public Organisation(String occupationPlace, String occupationTitle, String duties, LocalDate start, LocalDate finish) {
        this.occupationPlace = occupationPlace;
        this.occupationTitle = occupationTitle;
        this.duties = duties;
        this.start = start;
        this.finish = finish;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Organisation)) return false;
        Organisation that = (Organisation) o;
        return Objects.equals(occupationPlace, that.occupationPlace) &&
                Objects.equals(occupationTitle, that.occupationTitle) &&
                Objects.equals(duties, that.duties) &&
                Objects.equals(start, that.start) &&
                Objects.equals(finish, that.finish);
    }

    @Override
    public int hashCode() {
        return Objects.hash(occupationPlace, occupationTitle, duties, start, finish);
    }

    @Override
    public String toString() {
        return occupationPlace + "\n" + occupationTitle + "\n" + duties + "\n" + start + " " + finish;
    }
}
