package ru.webapp.model;

import java.util.List;
import java.util.Objects;

public class ListSection implements Section {
    private final List<String> listSections;

    public ListSection(List<String> listSections) {
        this.listSections = listSections;
    }

    public List<String> getListSection() {
        return listSections;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ListSection)) return false;
        ListSection that = (ListSection) o;
        return Objects.equals(listSections, that.listSections);
    }

    @Override
    public int hashCode() {
        return Objects.hash(listSections);
    }

    @Override
    public String toString() {
        return listSections.toString();
    }
}
