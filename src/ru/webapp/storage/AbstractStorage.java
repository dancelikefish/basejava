package ru.webapp.storage;

import ru.webapp.exception.ExistStorageException;
import ru.webapp.exception.NotExistStorageException;
import ru.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    protected abstract void saveInStorage(Resume r, Object searchKey);

    protected abstract void updateInStorage(Resume r, Object searchKey);

    protected abstract Resume getInStorage(Object searchKey);

    protected abstract void deleteInStorage(Object searchKey);

    protected abstract boolean isNotValid(Object searchKey);

    protected abstract Object getSearchKey(String uuid);

    @Override
    public void save(Resume r) {
        Object searchKey = getValidSearchKey(r.getUuid());
        saveInStorage(r, searchKey);
    }

    @Override
    public void update(Resume r) {
        Object searchKey = getNotValidSearchKey(r.getUuid());
        updateInStorage(r, searchKey);
    }

    @Override
    public Resume get(String uuid) {
        Object searchKey = getNotValidSearchKey(uuid);
        return getInStorage(searchKey);
    }

    @Override
    public void delete(String uuid) {
        Object searchKey = getNotValidSearchKey(uuid);
        deleteInStorage(searchKey);
    }

    private Object getValidSearchKey(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (!isNotValid(searchKey)) {
            throw new ExistStorageException(uuid);
        }
        return searchKey;
    }

    private Object getNotValidSearchKey(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (isNotValid(searchKey)) {
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }
}

