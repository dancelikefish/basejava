package javaOps.webapp.storage;

import javaOps.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected void saveInArrays(Resume r, int binaryValue) {
        binaryValue = -binaryValue - 1;
        System.arraycopy(storage, binaryValue, storage, binaryValue + 1, size - binaryValue);
        storage[binaryValue] = r;
    }

    @Override
    protected void deleteInArrays(int binaryValue) {
        System.arraycopy(storage, binaryValue + 1, storage, binaryValue, size - binaryValue - 1);
    }

    protected int getIndex(String uuid) {
        Resume searchKey = new Resume(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}
