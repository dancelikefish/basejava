package ru.webapp.model;

import java.util.Objects;

public class SimpleTextSection implements Section {
    private final String textSection;

    public String getTextSection() {
        return textSection;
    }

    public SimpleTextSection(String textSection) {
        Objects.requireNonNull(textSection, "textSection mustn't be null");
        this.textSection = textSection;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SimpleTextSection that = (SimpleTextSection) o;
        return textSection.equals(that.textSection);
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
