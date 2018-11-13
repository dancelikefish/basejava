package ru.webapp.storage;

import ru.webapp.exception.StorageException;
import ru.webapp.model.Resume;

import java.util.Arrays;
import java.util.List;

public abstract class AbstractArrayStorage extends AbstractStorage<Integer> {
    protected static final int STORAGE_LIMIT = 10000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    @Override
    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    protected void saveInStorage(Resume resume, Integer searchIndex) {
        if (size == STORAGE_LIMIT) {
            throw new StorageException("Storage is overflowed", resume.getUuid());
        } else
            saveInArray(resume, searchIndex);
        size++;
    }

    protected abstract void saveInArray(Resume resume, Integer searchIndex);

    @Override
    public void updateInStorage(Resume r, Integer searchIndex) {
        int index = searchIndex;
        storage[index] = r;
    }

    @Override
    protected Resume getFromStorage(Integer searchIndex) {
        int index = searchIndex;
        return storage[index];
    }

    @Override
    public void deleteFromStorage(Integer searchIndex) {
        int index = searchIndex;
        deleteInArray(index);
        storage[size - 1] = null;
        size--;
    }

    protected abstract void deleteInArray(int searchIndex);

    @Override
    public List<Resume> getCopyList() {
        return Arrays.asList(Arrays.copyOf(storage, size));
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    protected boolean isValid(Integer searchIndex) {
        return searchIndex < 0;
    }
}
