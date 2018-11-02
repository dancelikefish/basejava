package ru.webapp.storage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.webapp.exception.ExistStorageException;
import ru.webapp.exception.NotExistStorageException;
import ru.webapp.model.Resume;

import java.util.Arrays;
import java.util.List;

public class AbstractStorageTest {
    private Storage storage;

    private static final String UUID1 = "uuid1";
    private static final String UUID2 = "uuid2";
    private static final String UUID3 = "uuid3";
    private static final String UUID4 = "uuid4";

    private static final Resume R1 = new Resume(UUID1, "uuid1");
    private static final Resume R2 = new Resume(UUID2, "uuid1");
    private static final Resume R3 = new Resume(UUID3, "uuid3");
    private static final Resume R4 = new Resume(UUID4, "uuid4");
    private static final List<Resume> expectedResumes = Arrays.asList(R1, R2, R3);
    private static final List<Resume> expectedResumes2 = Arrays.asList(R1, R2);


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
        storage.save(new Resume(UUID4));
        Assert.assertEquals(R4, storage.get(UUID4));
        Assert.assertEquals(4, storage.size());
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() {
        storage.save(R3);
    }

    @Test(expected = AssertionError.class)
    public void update() {
        Resume r2 = new Resume(UUID2);
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

    @Test
    public void delete() {
        storage.delete(R3.getUuid());

        Assert.assertEquals(expectedResumes2, storage.getAllSorted());
        Assert.assertEquals(2, storage.size());
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