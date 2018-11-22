package ru.webapp.model;

public class PersonalPositionSection implements ResumeSection {
    private String textSection;

    public String getTextSection() {
        return textSection;
    }

    public void setTextSection(String textSection) {
        this.textSection = textSection;
    }

    public PersonalPositionSection(String textSection) {
        this.textSection = textSection;
    }

    @Override
    public String toString() {
        return textSection + "\n";
    }
}
