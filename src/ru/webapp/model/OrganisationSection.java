package ru.webapp.model;

import java.util.List;
import java.util.Objects;

public class OrganisationSection implements Section {
    private List<Organization> organisations;

    public OrganisationSection(List<Organization> organisations) {
        Objects.requireNonNull(organisations, "Organizations mustn't be null");
        this.organisations = organisations;
    }

    @Override
    public String toString() {
        return organisations.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrganisationSection that = (OrganisationSection) o;
        return Objects.equals(organisations, that.organisations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(organisations);
    }
}
