package ru.webapp.storage;

import ru.webapp.exception.StorageException;
import ru.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected void saveInStorage(Resume r, int binaryValue) {
        if (size == STORAGE_LIMIT) {
            throw new StorageException("Storage is overflowed", r.getUuid());
        } else
            binaryValue = -binaryValue - 1;
            System.arraycopy(storage, binaryValue, storage, binaryValue + 1, size - binaryValue);
            storage[binaryValue] = r;
            size++;
    }

    @Override
    protected void deleteInArrays(int binaryValue) {
        System.arraycopy(storage, binaryValue + 1, storage, binaryValue, size - binaryValue - 1);
    }

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}
