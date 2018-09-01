package javaOps.webapp.storage;

import javaOps.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    public void save(Resume r) {
        if (size == STORAGE_LIMIT) {
            System.out.println("Storage is overflowed");
        }
        int j = Arrays.binarySearch(storage, 0, size, r);
        if (j < 0) {
            j = -j - 1;
            System.arraycopy(storage, j, storage, j + 1, size - j);
            storage[j] = r;
            size++;
        }
        else
            System.out.println("Resume " + r.getUuid() + " isn't unique");
    }

    @Override
    public void update(Resume r) {

    }

    @Override
    public void delete(String uuid) {

    }

    @Override
    protected int getFoundIndex(String uuid) {
        ArrayStorage arrayStorage = new ArrayStorage();
        Resume keyValue = arrayStorage.get(uuid);
        return Arrays.binarySearch(storage, 0, size, keyValue);
    }

}
