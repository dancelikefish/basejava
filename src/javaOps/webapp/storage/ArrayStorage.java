package javaOps.webapp.storage;

import javaOps.webapp.model.Resume;

/**
 * Array based javaOps.webapp.storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {
    @Override
    protected void saveInArrays(Resume r, int index) {
        storage[size] = r;
    }

    @Override
    protected void deleteInArrays(int index) {
        storage[index] = storage[size - 1];
        storage[size - 1] = null;
    }

    protected int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }
}

/**
 * @return array, contains only Resumes in javaOps.webapp.storage (without null)
 */

