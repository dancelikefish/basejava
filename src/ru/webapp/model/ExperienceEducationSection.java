package ru.webapp.model;

import java.time.LocalDate;
import java.util.List;

public class ExperienceEducationSection implements ResumeSection {
    private String occupationPlace;
    private String occupationTitle;
    private String duties;
    private LocalDate start;
    private LocalDate finish;
    private List<ExperienceEducationSection> eSections;

    public ExperienceEducationSection(String occupationPlace, String occupationTitle, String duties, LocalDate start, LocalDate finish) {
        this.occupationPlace = occupationPlace;
        this.occupationTitle = occupationTitle;
        this.duties = duties;
        this.start = start;
        this.finish = finish;
    }

    public ExperienceEducationSection(List<ExperienceEducationSection> eSections) {
        this.eSections = eSections;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (ExperienceEducationSection s : eSections) {
            sb.append("\n" + s.occupationPlace + "\n" + s.getOccupationTitle() + "\n" + s.getDuties() + "\n" + s.getStart() + " " + s.getFinish() + "\n");
        }
        return sb.toString();
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

    public List<ExperienceEducationSection> geteSections() {
        return eSections;
    }

    public void seteSections(List<ExperienceEducationSection> eSections) {
        this.eSections = eSections;
    }
}
