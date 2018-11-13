package ru.webapp.storage;

import ru.webapp.model.Resume;

/**
 * Array based ru.webapp.storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    @Override
    protected void saveInArray(Resume resume, Integer index) {
        storage[size] = resume;
    }

    @Override
    protected void deleteInArray(int index) {
        storage[index] = storage[size - 1];
        storage[size - 1] = null;
    }

    @Override
    protected Integer getSearchKey(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }
}

