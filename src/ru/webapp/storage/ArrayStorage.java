package ru.webapp.storage;

import ru.webapp.exception.StorageException;
import ru.webapp.model.Resume;

/**
 * Array based ru.webapp.storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    @Override
    protected void saveInStorage(Resume r, Object searchKey) {
        if (size == STORAGE_LIMIT) {
            throw new StorageException("Storage is overflowed", r.getUuid());
        } else
            storage[size] = r;
        size++;

    }

    @Override
    protected boolean isValid(Object searchKey) {
        return (Integer) searchKey < 0;
    }

    @Override
    protected void deleteInArrays(int index) {
        storage[index] = storage[size - 1];
        storage[size - 1] = null;
    }

    @Override
    protected Object getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }
}

/**
 * @return array, contains only Resumes in ru.webapp.storage (without null)
 */

