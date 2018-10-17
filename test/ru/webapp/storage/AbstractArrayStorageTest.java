package ru.webapp.storage;

import ru.webapp.exception.ExistStorageException;
import ru.webapp.exception.NotExistStorageException;
import ru.webapp.exception.StorageException;
import ru.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static ru.webapp.storage.AbstractArrayStorage.STORAGE_LIMIT;

public abstract class AbstractArrayStorageTest {
    private Storage storage;

    private static final String UUID1 = "uuid1";
    private static final String UUID2 = "uuid2";
    private static final String UUID3 = "uuid3";

    private static final Resume R1 = new Resume(UUID1);
    private static final Resume R2 = new Resume(UUID2);
    private static final Resume R3 = new Resume(UUID3);

    private static final Resume[] expectedResumes = {R1, R2, R3};

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(R1);
        storage.save(R2);
        storage.save(R3);
    }

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Test
    public void clear() {
        storage.clear();
        Assert.assertEquals(0, storage.size());
    }

    @Test(expected = StorageException.class)
    public void save() {
        storage.save(new Resume("uuid4"));
        Assert.assertEquals(new Resume("uuid4"), storage.get("uuid4"));
        Assert.assertEquals(4, storage.size());
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() {
        storage.save(R3);
    }

    @Test
    public void saveOverflow() {
        try {
            int i = 5;
            while (i <= STORAGE_LIMIT) {
                storage.save(new Resume("uuid" + i));
                i++;
            }
        } catch (StorageException e) {
            Assert.fail("Exception is thrown");
        }

        storage.save(new Resume("uuid10001"));
    }

    @Test
    public void update() {
        storage.update(R1);

        Assert.assertArrayEquals(expectedResumes, storage.getAll());
        Assert.assertEquals(3, storage.size());

    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        storage.update(new Resume("dummy"));
    }

    @Test
    public void get() {

        storage.get(R1.getUuid());

        Assert.assertArrayEquals(expectedResumes, storage.getAll());
        Assert.assertEquals(3, storage.size());

    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get("dummy");
    }

    @Test
    public void delete() {
        Assert.assertArrayEquals(expectedResumes, storage.getAll());
        Assert.assertEquals(3, storage.size());
        storage.delete(R1.getUuid());

        Resume[] expectedResumes2 = {new Resume("uuid3"), new Resume("uuid2")};
        Assert.assertArrayEquals(expectedResumes2, storage.getAll());
        Assert.assertEquals(2, storage.size());
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        storage.delete("dummy");
    }

    @Test
    public void getAll() {
        Assert.assertArrayEquals(expectedResumes, storage.getAll());
    }

    @Test
    public void size() {
        Assert.assertEquals(3, storage.size());
    }
}