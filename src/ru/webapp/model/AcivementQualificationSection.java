package ru.webapp.model;

import java.util.ArrayList;
import java.util.List;

public class AcivementQualificationSection implements ResumeSection {
    private List<String> listSection = new ArrayList<>();

    public List<String> getListSection() {
        return listSection;
    }

    public void setListSection(List<String> listSection) {
        this.listSection = listSection;
    }

    public AcivementQualificationSection(List<String> listSection) {
        this.listSection = listSection;
    }
}
