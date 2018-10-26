package ru.webapp.storage;

import ru.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage extends AbstractStorage {
    protected static final int STORAGE_LIMIT = 10000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    @Override
    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    public void updateInStorage(Resume r, Resume searchKey) {
        int index = searchKey.getSearchKey();
        storage[index] = r;
    }

    @Override
    protected Resume getInStorage(String uuid, Resume searchKey) {
        int index = searchKey.getSearchKey();
        return storage[index];
    }

    @Override
    public void deleteInStorage(String uuid, Resume searchKey) {
        int index = searchKey.getSearchKey();
        deleteInArrays(index);
        storage[size - 1] = null;
        size--;
    }

    protected abstract void deleteInArrays(int index);

    @Override
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    @Override
    public int size() {
        return size;
    }
}
