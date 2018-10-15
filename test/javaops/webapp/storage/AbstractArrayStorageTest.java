package javaops.webapp.storage;

import javaops.webapp.exception.NotExistStorageException;
import javaops.webapp.exception.StorageException;
import javaops.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public abstract class AbstractArrayStorageTest {
    Storage storage;

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(new Resume("uuid1"));
        storage.save(new Resume("uuid2"));
        storage.save(new Resume("uuid3"));
    }

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Test
    public void clear() {
        storage.clear();
        Assert.assertEquals(0, storage.size());
    }

    @Test
    public void save() {
        int maxStorageSize = 10000;
        int i = 4;
        while (i <= maxStorageSize) {
            storage.save(new Resume("uuid"+ i));
            i++;
        }
    }

    @Test(expected = StorageException.class)
    public void saveOverflow() {
        int maxStorageSize = 10001;
        int i = 4;
        while (i <= maxStorageSize) {
            storage.save(new Resume("uuid"+ i));
            i++;
        }
    }

    @Test
    public void update() {
        storage.update(new Resume("uuid1"));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        storage.update(new Resume("dummy"));
    }

    @Test
    public void get() {
        storage.get("uuid1");
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get("dummy");
    }

    @Test
    public void delete() {
        storage.delete("uuid1");
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        storage.delete("dummy");
    }

    @Test
    public void getAll() {
        Resume[] expectedResumes = {new Resume("uuid1"), new Resume("uuid2"), new Resume("uuid3")};
        Assert.assertArrayEquals(expectedResumes, storage.getAll());
    }

    @Test
    public void size() {
        Assert.assertEquals(3, storage.size());
    }
}