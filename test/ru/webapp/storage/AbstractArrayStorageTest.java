package ru.webapp.storage;

import ru.webapp.exception.ExistStorageException;
import ru.webapp.exception.NotExistStorageException;
import ru.webapp.exception.StorageException;
import ru.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public abstract class AbstractArrayStorageTest {
    Storage storage;

    Resume r1 = new Resume("uuid1");
    Resume r2 = new Resume("uuid2");
    Resume r3 = new Resume("uuid3");

    Resume[] expectedResumes = {r1, r2, r3};

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(r1);
        storage.save(r2);
        storage.save(r3);
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

        try {
            int maxStorageSize = 10000;
            int i = 5;
            while (i <= maxStorageSize) {
                storage.save(new Resume("uuid" + i));
                i++;
            }
        } catch (StorageException e) {
            Assert.fail("Exception is thrown");
        }

        storage.save(new Resume("uuid10001"));
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() {
        storage.save(new Resume("uuid3"));
    }

    @Test
    public void update() {
        Assert.assertArrayEquals(expectedResumes, storage.getAll());
        Assert.assertEquals(3, storage.size());
        storage.update(new Resume("uuid1"));

        Assert.assertArrayEquals(expectedResumes, storage.getAll());
        Assert.assertEquals(3, storage.size());

    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        storage.update(new Resume("dummy"));
    }

    @Test
    public void get() {
        Assert.assertArrayEquals(expectedResumes, storage.getAll());
        Assert.assertEquals(3, storage.size());
        storage.get("uuid1");

        Assert.assertArrayEquals(expectedResumes, storage.getAll());
        Assert.assertEquals(3, storage.size());

    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get("dummy");
    }

    @Test
    public void deleteForAST() {
        Assert.assertArrayEquals(expectedResumes, storage.getAll());
        Assert.assertEquals(3, storage.size());
        storage.delete("uuid1");

        Resume[] expectedResumes2 = {new Resume("uuid3"), new Resume("uuid2")};
        Assert.assertArrayEquals(expectedResumes2, storage.getAll());
        Assert.assertEquals(2, storage.size());
    }
    @Test
    public void deleteForSAST() {
        Assert.assertArrayEquals(expectedResumes, storage.getAll());
        Assert.assertEquals(3, storage.size());
        storage.delete("uuid1");

        Resume[] expectedResumes2 = {new Resume("uuid2"), new Resume("uuid3")};
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