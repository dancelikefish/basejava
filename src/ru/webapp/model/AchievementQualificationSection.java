package ru.webapp.model;

import java.util.List;

public class AchievementQualificationSection implements ResumeSection {
    private List<String> listSection;

    public List<String> getListSection() {
        return listSection;
    }

    public void setListSection(List<String> listSection) {
        this.listSection = listSection;
    }

    public AchievementQualificationSection(List<String> listSection) {
        this.listSection = listSection;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (String s : listSection) {
            sb.append(s + "\n");
        }
        return sb.toString();
    }
}
