package ru.webapp.storage;

import ru.webapp.model.Resume;

/**
 * Array based ru.webapp.storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    @Override
    protected void saveInArray(Resume r, Object index) {
        storage[size] = r;
    }

    @Override
    protected void deleteInArray(int index) {
        storage[index] = storage[size - 1];
        storage[size - 1] = null;
    }

    @Override
    protected Object getSearchKey(String uuid) {
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

