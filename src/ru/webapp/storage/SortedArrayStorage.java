package ru.webapp.storage;

import ru.webapp.exception.StorageException;
import ru.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected void saveInStorage(Resume r, Object binaryValue) {
        int index = (Integer) binaryValue;
        if (size == STORAGE_LIMIT) {
            throw new StorageException("Storage is overflowed", r.getUuid());
        } else
            index = -index - 1;
        System.arraycopy(storage, index, storage, index + 1, size - index);
        storage[index] = r;
        size++;
    }

    @Override
    protected boolean isValid(Object searchKey) {
        return (Integer) searchKey < 0;
    }

    @Override
    protected void deleteInArrays(int binaryValue) {
        System.arraycopy(storage, binaryValue + 1, storage, binaryValue, size - binaryValue - 1);
    }

    @Override
    protected Object getIndex(String uuid) {
        Resume searchKey = new Resume(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}
