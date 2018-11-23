package ru.webapp.model;

import java.util.Objects;
import java.util.UUID;

/**
 * Initial resume class
 */
public class Resume implements ResumeSection {
    private final String uuid;
    private final String fullName;
    private ResumeSection personalSection;
    private ResumeSection positionSection;
    private ResumeSection achievementSection;
    private ResumeSection qualificationSection;
    private ResumeSection experienceSection;
    private ResumeSection educationSection;

    public Resume(String fullName,ResumeSection positionSection, ResumeSection personalSection,
                  ResumeSection achievementSection, ResumeSection qualificationSection,
                  ResumeSection experienceSection, ResumeSection educationSection  ) {

        this(UUID.randomUUID().toString(), fullName, personalSection, positionSection, achievementSection, qualificationSection, experienceSection, educationSection);
    }

    public Resume(String uuid, String fullName, ResumeSection positionSection, ResumeSection personalSection,
                  ResumeSection achievementSection, ResumeSection qualificationSection,
                  ResumeSection experienceSection, ResumeSection educationSection ) {

        Objects.requireNonNull(uuid, "uuid mustn't be null");
        Objects.requireNonNull(fullName, "fullName mustn't be null");
        this.uuid = uuid;
        this.fullName = fullName;
        this.positionSection = positionSection;
        this.personalSection = personalSection;
        this.achievementSection = achievementSection;
        this.qualificationSection = qualificationSection;
        this.experienceSection = experienceSection;
        this.educationSection = educationSection;
    }

    public Resume(String fullName) {
        this(UUID.randomUUID().toString(), fullName);
    }

    public Resume(String uuid, String fullName) {
        Objects.requireNonNull(uuid, "uuid mustn't be null");
        Objects.requireNonNull(fullName, "fullName mustn't be null");
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
        return Objects.equals(uuid, resume.uuid) &&
                Objects.equals(fullName, resume.fullName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, fullName);
    }

    @Override
    public String toString() {
        return fullName + "\n" + ContactType.CELLPHONENUMBER.printContacts() + "\n" + SectionType.PERSONAL.getTitle() + "\n" + personalSection.toString() + "\n" + SectionType.OBJECTIVE.getTitle() + "\n" + positionSection.toString() +
                "\n" + SectionType.ACHIEVEMENT.getTitle() + "\n" + achievementSection.toString() + "\n" + SectionType.QUALIFICATIONS.getTitle() + "\n" + qualificationSection.toString() + "\n " +
                SectionType.EXPEREINCE.getTitle() + "\n" + experienceSection.toString() + "\n" + SectionType.EDUCATION.getTitle() + "\n" + educationSection.toString();
    }
}
