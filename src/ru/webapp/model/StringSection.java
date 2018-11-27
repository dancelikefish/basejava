package ru.webapp.model;

import java.util.Objects;

public class StringSection implements Section {
    private final String textSection;

    public String getTextSection() {
        return textSection;
    }

    public StringSection(String textSection) {
        this.textSection = textSection;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StringSection)) return false;
        StringSection that = (StringSection) o;
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
