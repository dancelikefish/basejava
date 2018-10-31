package ru.webapp.storage;

import ru.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected void saveInArray(Resume r, Object binaryValue) {
        int index = (Integer) binaryValue;
        index = -index - 1;
        System.arraycopy(storage, index, storage, index + 1, size - index);
        storage[index] = r;
    }

    @Override
    protected void deleteInArray(int binaryValue) {
        System.arraycopy(storage, binaryValue + 1, storage, binaryValue, size - binaryValue - 1);
    }

    @Override
    protected Object getIndex(String uuid) {
        Resume searchKey = new Resume(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}
