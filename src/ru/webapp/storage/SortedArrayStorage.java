package ru.webapp.storage;

import ru.webapp.exception.StorageException;
import ru.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected void saveInStorage(Resume r, Resume binaryValue) {
        int index = binaryValue.getSearchKey();
        if (size == STORAGE_LIMIT) {
            throw new StorageException("Storage is overflowed", r.getUuid());
        } else
            index = -index - 1;
            System.arraycopy(storage, index, storage, index + 1, size - index);
            storage[index] = r;
            size++;
    }

    @Override
    protected boolean isValid(Resume searchKey) {
        return searchKey.getSearchKey() < 0;
    }

    @Override
    protected void deleteInArrays(int binaryValue) {
        System.arraycopy(storage, binaryValue + 1, storage, binaryValue, size - binaryValue - 1);
    }

    @Override
    protected Resume getIndex(String uuid) {
        Resume searchKey = new Resume(uuid);
        return new Resume(Arrays.binarySearch(storage, 0, size, searchKey));
    }
}
