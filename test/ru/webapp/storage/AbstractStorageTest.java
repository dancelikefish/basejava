package ru.webapp.storage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.webapp.exception.ExistStorageException;
import ru.webapp.exception.NotExistStorageException;
import ru.webapp.model.*;
import ru.webapp.util.ResumeTestData;

import java.util.*;

public class AbstractStorageTest {
    protected Storage storage;

    protected static final String UUID1 = "uuid1";
    protected static final String UUID2 = "uuid2";
    protected static final String UUID3 = "uuid3";
    protected static final String UUID4 = "uuid4";

    protected static final Resume R1 = new Resume(UUID1, UUID1);
    protected static final Resume R2 = new Resume(UUID2, UUID2);
    protected static final Resume R3 = new Resume(UUID3, UUID3);
    protected static final Resume R4 = new Resume(UUID4, UUID4);
    protected static final List<Resume> expectedResumes = Arrays.asList(R1, R2, R3);

    private static Map<ContactType, String> contacts = new EnumMap<>(ContactType.class);
    private static List<String> achievements = new ArrayList<>();
    private static List<String> qualifications = new ArrayList<>();
    private static List<Organization> occupationPlaces = new ArrayList<>();
    private static List<Organization> educationPlaces = new ArrayList<>();

    static {
        R1.setContacts(ResumeTestData.fillContactSection(contacts));
        R2.setContacts(ResumeTestData.fillContactSection(contacts));
        R3.setContacts(ResumeTestData.fillContactSection(contacts));
        R4.setContacts(ResumeTestData.fillContactSection(contacts));

        R1.addSection(SectionType.PERSONAL, new SimpleTextSection(ResumeTestData.fillPersonalSection()));
        R1.addSection(SectionType.OBJECTIVE, new SimpleTextSection(ResumeTestData.fillPositionSection()));
        R1.addSection(SectionType.ACHIEVEMENT, new ListSection(ResumeTestData.fillAchievementSection(achievements)));
        R1.addSection(SectionType.QUALIFICATIONS, new ListSection(ResumeTestData.fillQualificationSection(qualifications)));
        R1.addSection(SectionType.EXPERIENCE, new OrganizationSection(ResumeTestData.fillExperienceSection(occupationPlaces)));
        R1.addSection(SectionType.EDUCATION, new OrganizationSection(ResumeTestData.fillEducationSection(educationPlaces)));

        R2.addSection(SectionType.PERSONAL, new SimpleTextSection(ResumeTestData.fillPersonalSection()));
        R2.addSection(SectionType.OBJECTIVE, new SimpleTextSection(ResumeTestData.fillPositionSection()));
        R2.addSection(SectionType.ACHIEVEMENT, new ListSection(ResumeTestData.fillAchievementSection(achievements)));
        R2.addSection(SectionType.QUALIFICATIONS, new ListSection(ResumeTestData.fillQualificationSection(qualifications)));
        R2.addSection(SectionType.EXPERIENCE, new OrganizationSection(ResumeTestData.fillExperienceSection(occupationPlaces)));
        R2.addSection(SectionType.EDUCATION, new OrganizationSection(ResumeTestData.fillEducationSection(educationPlaces)));

        R3.addSection(SectionType.PERSONAL, new SimpleTextSection(ResumeTestData.fillPersonalSection()));
        R3.addSection(SectionType.OBJECTIVE, new SimpleTextSection(ResumeTestData.fillPositionSection()));
        R3.addSection(SectionType.ACHIEVEMENT, new ListSection(ResumeTestData.fillAchievementSection(achievements)));
        R3.addSection(SectionType.QUALIFICATIONS, new ListSection(ResumeTestData.fillQualificationSection(qualifications)));
        R3.addSection(SectionType.EXPERIENCE, new OrganizationSection(ResumeTestData.fillExperienceSection(occupationPlaces)));
        R3.addSection(SectionType.EDUCATION, new OrganizationSection(ResumeTestData.fillEducationSection(educationPlaces)));

        R4.addSection(SectionType.PERSONAL, new SimpleTextSection(ResumeTestData.fillPersonalSection()));
        R4.addSection(SectionType.OBJECTIVE, new SimpleTextSection(ResumeTestData.fillPositionSection()));
        R4.addSection(SectionType.ACHIEVEMENT, new ListSection(ResumeTestData.fillAchievementSection(achievements)));
        R4.addSection(SectionType.QUALIFICATIONS, new ListSection(ResumeTestData.fillQualificationSection(qualifications)));
        R4.addSection(SectionType.EXPERIENCE, new OrganizationSection(ResumeTestData.fillExperienceSection(occupationPlaces)));
        R4.addSection(SectionType.EDUCATION, new OrganizationSection(ResumeTestData.fillEducationSection(educationPlaces)));
    }

    public AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(R2);
        storage.save(R1);
        storage.save(R3);
    }

    @Test
    public void clear() {
        storage.clear();
        Assert.assertEquals(0, storage.size());
    }

    @Test()
    public void save() {
        storage.save(R4);
        Assert.assertEquals(R4, storage.get(UUID4));
        Assert.assertEquals(4, storage.size());
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() {
        storage.save(R3);
    }

    @Test(expected = AssertionError.class)

    public void update() {
        Resume r2 = new Resume(UUID2, UUID2);
        storage.update(r2);
        Assert.assertSame(R2, r2);
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        storage.update(new Resume("dummy"));
    }

    @Test
    public void get() {
        storage.get(R1.getUuid());

        Assert.assertEquals(expectedResumes, storage.getAllSorted());
        Assert.assertEquals(3, storage.size());
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get("dummy");
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() {
        storage.delete(R3.getUuid());
        Assert.assertEquals(2, storage.size());
        Assert.assertEquals(storage.get(UUID3), R3);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        storage.delete("dummy");
    }

    @Test
    public void getAll() {
        Assert.assertEquals(expectedResumes, storage.getAllSorted());
    }

    @Test
    public void size() {
        Assert.assertEquals(3, storage.size());
    }
}