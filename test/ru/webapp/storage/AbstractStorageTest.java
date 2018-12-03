package ru.webapp.storage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.webapp.exception.ExistStorageException;
import ru.webapp.exception.NotExistStorageException;
import ru.webapp.model.*;
import ru.webapp.util.ResumeTestData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    private static List<String> achievements = new ArrayList<>();
    private static List<String> qualifications = new ArrayList<>();
    private static List<Organization> occupationPlaces = new ArrayList<>();
    private static List<Organization> educationPlaces = new ArrayList<>();

    static {
        R1.addContact(ContactType.CELLPHONENUMBER, "+7(921) 855-0482");
        R1.addContact(ContactType.SKYPE, "gkislin");
        R1.addContact(ContactType.MAIL, "gkislin@yandex.ru");
        R1.addContact(ContactType.LINKEDIN, "https://www.linkedin.com/in/gkislin");
        R1.addContact(ContactType.GITHUB, "https://github.com/gkislin");
        R1.addContact(ContactType.STACKOVERFLOW, "https://stackoverflow.com/users/548473/gkislin");

        R2.addContact(ContactType.CELLPHONENUMBER, "+7(921) 222-2222");
        R2.addContact(ContactType.SKYPE, "test2");
        R2.addContact(ContactType.MAIL, "test2@org2.ru");
        R2.addContact(ContactType.LINKEDIN, "https://www.linkedin.com/in/test2");
        R2.addContact(ContactType.GITHUB, "https://github.com/test2");
        R2.addContact(ContactType.STACKOVERFLOW, "https://stackoverflow.com/users/548473/test2");

        R3.addContact(ContactType.CELLPHONENUMBER, "+7(921) 333-3333");
        R3.addContact(ContactType.SKYPE, "test3");
        R3.addContact(ContactType.MAIL, "test3@org.ru");
        R3.addContact(ContactType.LINKEDIN, "https://www.linkedin.com/in/test3");
        R3.addContact(ContactType.STACKOVERFLOW, "https://stackoverflow.com/users/548473/test3");

        R4.addContact(ContactType.CELLPHONENUMBER, "+7(921) 444-4444");
        R4.addContact(ContactType.MAIL, "test@org.ru");
        R4.addContact(ContactType.GITHUB, "https://github.com/test4");
        R4.addContact(ContactType.STACKOVERFLOW, "https://stackoverflow.com/users/548473/test4");

        R1.addSection(SectionType.PERSONAL, new SimpleTextSection("Аналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры."));
        R1.addSection(SectionType.OBJECTIVE, new SimpleTextSection("Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям"));
        R1.addSection(SectionType.ACHIEVEMENT, new ListSection(ResumeTestData.fillListSection(achievements)));
        R1.addSection(SectionType.QUALIFICATIONS, new ListSection(ResumeTestData.fill2ListSection(qualifications)));
        R1.addSection(SectionType.EXPERIENCE, new OrganizationSection(ResumeTestData.fillExperienceSection(occupationPlaces)));
        R1.addSection(SectionType.EDUCATION, new OrganizationSection(ResumeTestData.fillEducationSection(educationPlaces)));

        R2.addSection(SectionType.PERSONAL, new SimpleTextSection("Личные качества тест2"));
        R2.addSection(SectionType.OBJECTIVE, new SimpleTextSection("Позиция тест2"));
        R2.addSection(SectionType.ACHIEVEMENT, new ListSection(ResumeTestData.fillListSection(achievements)));
        R2.addSection(SectionType.QUALIFICATIONS, new ListSection(ResumeTestData.fill2ListSection(qualifications)));
        R2.addSection(SectionType.EXPERIENCE, new OrganizationSection(ResumeTestData.fillExperienceSection(occupationPlaces)));
        R2.addSection(SectionType.EDUCATION, new OrganizationSection(ResumeTestData.fillEducationSection(educationPlaces)));

        R3.addSection(SectionType.PERSONAL, new SimpleTextSection("Личные качества тест3"));
        R3.addSection(SectionType.OBJECTIVE, new SimpleTextSection("Позиция тест3"));
        R3.addSection(SectionType.ACHIEVEMENT, new ListSection(ResumeTestData.fillListSection(achievements)));
        R3.addSection(SectionType.QUALIFICATIONS, new ListSection(ResumeTestData.fill2ListSection(qualifications)));
        R3.addSection(SectionType.EXPERIENCE, new OrganizationSection(ResumeTestData.fillExperienceSection(occupationPlaces)));
        R3.addSection(SectionType.EDUCATION, new OrganizationSection(ResumeTestData.fillEducationSection(educationPlaces)));

        R4.addSection(SectionType.PERSONAL, new SimpleTextSection("Личные качества тест4"));
        R4.addSection(SectionType.OBJECTIVE, new SimpleTextSection("Позиция тест4"));
        R4.addSection(SectionType.ACHIEVEMENT, new ListSection(ResumeTestData.fillListSection(achievements)));
        R4.addSection(SectionType.QUALIFICATIONS, new ListSection(ResumeTestData.fill2ListSection(qualifications)));
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