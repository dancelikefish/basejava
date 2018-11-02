package ru.webapp.storage;

import ru.webapp.exception.StorageException;
import ru.webapp.model.Resume;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static ru.webapp.model.Resume.FULLNAME_COMPARATOR;

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
    protected void saveInStorage(Resume r, Object searchIndex) {
        if (size == STORAGE_LIMIT) {
            throw new StorageException("Storage is overflowed", r.getUuid());
        } else
            saveInArray(r, searchIndex);
            size++;
    }

    protected abstract void saveInArray(Resume r, Object searchIndex);

    @Override
    public void updateInStorage(Resume r, Object searchIndex) {
        int index = (Integer) searchIndex;
        storage[index] = r;
    }

    @Override
    protected Resume getInStorage(Object searchIndex) {
        int index = (Integer) searchIndex;
        return storage[index];
    }

    @Override
    public void deleteInStorage(Object searchIndex) {
        int index = (Integer) searchIndex;
        deleteInArray(index);
        storage[size - 1] = null;
        size--;
    }

    protected abstract void deleteInArray(int searchIndex);

    @Override
    public List<Resume> getAllSorted() {
        Resume[] resumes = Arrays.copyOf(storage, size);
        List<Resume> list = new ArrayList<>(Arrays.asList(resumes));
        list.sort(FULLNAME_COMPARATOR);
        return list;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    protected boolean isNotValid(Object searchIndex) {
        return (Integer) searchIndex < 0;
    }
}
