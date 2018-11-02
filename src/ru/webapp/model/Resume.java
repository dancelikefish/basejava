package ru.webapp.model;

import java.util.Comparator;
import java.util.Objects;
import java.util.UUID;

/**
 * Initial resume class
 */
public class Resume {
    private final String uuid;
    private String fullName;

    public Resume() {
        this(UUID.randomUUID().toString());
    }

    public Resume(String uuid) {
        this.uuid = uuid;
    }

    public Resume(String uuid, String fullName) {
        this.uuid = uuid;
        this.fullName = fullName;
    }

    public String getUuid() {
        return uuid;
    }

    public String getFullName() {
        return fullName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Resume resume = (Resume) o;
        return uuid.equals(resume.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid);
    }

    @Override
    public String toString() {
        return uuid;
    }

    public static final Comparator<Resume> FULLNAME_COMPARATOR = (o1, o2) -> {
        if (o1.getFullName().compareTo(o2.getFullName())== 0) {
            return o1.getUuid().compareTo(o2.getUuid());
        }
        return o1.getFullName().compareTo(o2.getFullName());
    };
}
