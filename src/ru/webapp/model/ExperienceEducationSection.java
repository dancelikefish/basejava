package ru.webapp.model;

import java.util.List;

public class ExperienceEducationSection implements ResumeSection {
    private List<ExperienceEducation> eeSections;

    public ExperienceEducationSection(List<ExperienceEducation> eeSections) {
        this.eeSections = eeSections;
    }

    @Override
    public String toString() {
        return eeSections.toString() + " ";
    }
}
