package ru.webapp.storage;

import org.junit.Assert;
import org.junit.Test;
import ru.webapp.exception.StorageException;
import ru.webapp.model.Resume;

import static ru.webapp.storage.AbstractArrayStorage.STORAGE_LIMIT;

public abstract class AbstractArrayStorageTest extends AbstractStorageTest {

    public AbstractArrayStorageTest(Storage storage) {
        super(storage);
    }

    @Test(expected = StorageException.class)
    public void saveOverflow() {
        try {
            for (int i = 4; i <= STORAGE_LIMIT; i++) {
                storage.save(new Resume("uuid" + i));
            }
        } catch (StorageException e) {
            Assert.fail("Exception is thrown");
        }
        storage.save(new Resume("uuid10001"));
    }
}