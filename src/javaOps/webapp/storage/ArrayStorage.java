package javaOps.webapp.storage;

import javaOps.webapp.model.Resume;

/**
 * Array based javaOps.webapp.storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    public void save(Resume r) {
        if (getIndex(r.getUuid()) == -1) {
            if (size < storage.length) {
                storage[size] = r;
                size++;
            } else {
                System.out.println("Storage is overflowed");
            }
        } else {
            System.out.println("Resume " + r.getUuid() + " isn't unique");
        }
    }

    public void update(Resume r) {
        int index = getIndex(r.getUuid());
        if (index != -1) {
            storage[index] = r;
        } else {
            System.out.println("Resume " + r.getUuid() + " doesn't exist");
        }
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index != -1) {
            storage[index] = storage[size - 1];
            storage[size - 1] = null;
            size--;
        } else {
            System.out.println("Resume " + uuid + " doesn't exist");
        }
    }

    protected int getIndex(String uuid) {
        int getIndex = -1;
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equals(uuid)) {
                getIndex = i;
            }
        }
        return getIndex;
    }

    /**
     * @return array, contains only Resumes in javaOps.webapp.storage (without null)
     */

}
