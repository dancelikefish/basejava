package ru.webapp.storage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.webapp.Config;
import ru.webapp.exception.ExistStorageException;
import ru.webapp.exception.NotExistStorageException;
import ru.webapp.model.ContactType;
import ru.webapp.model.Resume;
import ru.webapp.model.SectionType;
import ru.webapp.model.SimpleTextSection;
import ru.webapp.util.ResumeTestData;

import java.io.File;
import java.util.*;

public class AbstractStorageTest {
    protected Storage storage;
    protected static final File STORAGE_DIR = Config.get().getStorageDir();

    protected static final String UUID1 = UUID.randomUUID().toString();
    protected static final String UUID2 = UUID.randomUUID().toString();
    protected static final String UUID3 = UUID.randomUUID().toString();
    protected static final String UUID4 = UUID.randomUUID().toString();
    protected static final String UUID5 = UUID.randomUUID().toString();

    protected static final String NAME1 = "Test Name 1";
    protected static final String NAME2 = "Test Name 2";
    protected static final String NAME3 = "Test Name 3";
    protected static final String NAME4 = "Test Name 4";
    protected static final String NAME5 = "Test Name 5";

    protected static final Resume R1 = new Resume(UUID1, NAME1);
    protected static final Resume R2 = new Resume(UUID2, NAME2);
    protected static final Resume R3 = new Resume(UUID3, NAME3);
    protected static final Resume R4 = new Resume(UUID4, NAME4);
    protected static final Resume R5 = new Resume(UUID5, NAME5);
    protected static final List<Resume> expectedResumes = Arrays.asList(R1, R2, R3);

    static {
//        ResumeTestData.fillWholeResume(R1);
//        ResumeTestData.fillWholeResume(R2);
//        ResumeTestData.fillWholeResume(R3);
//        ResumeTestData.fillWholeResume(R4);
//        ResumeTestData.fillWholeResume(R5);
        Map<ContactType, String> contacts = new EnumMap<>(ContactType.class);
//        R1.setContacts(ResumeTestData.fillContactSection(contacts));
//        R2.setContacts(ResumeTestData.fillContactSection(contacts));
//        R3.setContacts(ResumeTestData.fillContactSection(contacts));
//        R4.setContacts(ResumeTestData.fillContactSection(contacts));
//        R5.setContacts(ResumeTestData.fillContactSection(contacts));
        R1.addSection(SectionType.PERSONAL, new SimpleTextSection(ResumeTestData.fillPersonalSection()));
//        R1.addSection(SectionType.OBJECTIVE, new SimpleTextSection(ResumeTestData.fillPositionSection()));
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