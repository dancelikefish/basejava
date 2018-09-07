package javaOps.webapp.storage;

import javaOps.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    public void save(Resume r) {
        if (size == STORAGE_LIMIT) {
            System.out.println("Storage is overflowed");
        }
        int binaryValue = getFoundIndex(r.getUuid());
        if (binaryValue < 0) {
            binaryValue = -binaryValue - 1;
            System.arraycopy(storage, binaryValue, storage, binaryValue + 1, size - binaryValue);
            storage[binaryValue] = r;
            size++;
        } else
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
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}
