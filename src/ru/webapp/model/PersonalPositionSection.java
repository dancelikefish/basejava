package ru.webapp.model;

import java.util.Objects;

public class PersonalPositionSection implements ResumeSection {
    private final String textSection;

    public String getTextSection() {
        return textSection;
    }

    public PersonalPositionSection(String textSection) {
        this.textSection = textSection;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PersonalPositionSection)) return false;
        PersonalPositionSection that = (PersonalPositionSection) o;
        return Objects.equals(textSection, that.textSection);
    }

    @Override
    public int hashCode() {
        return Objects.hash(textSection);
    }

    @Override
    public String toString() {
        return textSection;
    }
}
