package ru.webapp.storage;

import ru.webapp.exception.StorageException;
import ru.webapp.model.Resume;

/**
 * Array based ru.webapp.storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    @Override
    protected void saveInStorage(Resume r, Resume searchKey) {
        if (size == STORAGE_LIMIT) {
            throw new StorageException("Storage is overflowed", r.getUuid());
        }
        else
            storage[size] = r;
            size++;

    }

    @Override
    protected boolean isValid(Resume searchKey) {
        return searchKey.getSearchKey() < 0;
    }

    @Override
    protected void deleteInArrays(int index) {
        storage[index] = storage[size - 1];
        storage[size - 1] = null;
    }

    @Override
    protected Resume getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return new Resume(i);
            }
        }
        return new Resume(-1);
    }
}

/**
 * @return array, contains only Resumes in ru.webapp.storage (without null)
 */

