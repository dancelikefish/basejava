package ru.webapp.model;

import java.time.LocalDate;

public class ExpereinceEducationSection implements ResumeSection {
    private String occupationPlace;
    private String occupationTitle;
    private String duties;
    private LocalDate start;
    private LocalDate finish;

    public ExpereinceEducationSection(String occupationPlace, String occupationTitle, String duties, LocalDate start, LocalDate finish) {
        this.occupationPlace = occupationPlace;
        this.occupationTitle = occupationTitle;
        this.duties = duties;
        this.start = start;
        this.finish = finish;
    }

    public String getOccupationPlace() {
        return occupationPlace;
    }

    public void setOccupationPlace(String occupationPlace) {
        this.occupationPlace = occupationPlace;
    }

    public String getOccupationTitle() {
        return occupationTitle;
    }

    public void setOccupationTitle(String occupationTitle) {
        this.occupationTitle = occupationTitle;
    }

    public String getDuties() {
        return duties;
    }

    public void setDuties(String duties) {
        this.duties = duties;
    }

    public LocalDate getStart() {
        return start;
    }

    public void setStart(LocalDate start) {
        this.start = start;
    }

    public LocalDate getFinish() {
        return finish;
    }

    public void setFinish(LocalDate finish) {
        this.finish = finish;
    }
}
