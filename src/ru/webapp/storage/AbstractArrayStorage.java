package ru.webapp.storage;

import ru.webapp.exception.StorageException;
import ru.webapp.model.Resume;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    protected void saveInStorage(Resume resume, Object searchIndex) {
        if (size == STORAGE_LIMIT) {
            throw new StorageException("Storage is overflowed", resume.getUuid());
        } else
            saveInArray(resume, searchIndex);
            size++;
    }

    protected abstract void saveInArray(Resume resume, Object searchIndex);

    @Override
    public void updateInStorage(Resume r, Object searchIndex) {
        int index = (Integer) searchIndex;
        storage[index] = r;
    }

    @Override
    protected Resume getFromStorage(Object searchIndex) {
        int index = (Integer) searchIndex;
        return storage[index];
    }

    @Override
    public void deleteFromStorage(Object searchIndex) {
        int index = (Integer) searchIndex;
        deleteInArray(index);
        storage[size - 1] = null;
        size--;
    }

    protected abstract void deleteInArray(int searchIndex);

    @Override
    public List<Resume> getList() {
        Resume[] resumes = Arrays.copyOf(storage, size);
        return new ArrayList<>(Arrays.asList(resumes));
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    protected boolean isValid(Object searchIndex) {
        return (Integer) searchIndex < 0;
    }
}
