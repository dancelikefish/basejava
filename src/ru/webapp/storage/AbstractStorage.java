package ru.webapp.storage;

import ru.webapp.exception.ExistStorageException;
import ru.webapp.exception.NotExistStorageException;
import ru.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    @Override
    public void save(Resume r) {
        Object searchKey = getSearchKey(r.getUuid());
        if (!isNotValid(searchKey)) {
            throw new ExistStorageException(r.getUuid());
        } else {
            saveInStorage(r, searchKey);
        }
    }

    protected abstract void saveInStorage(Resume r, Object searchKey);

    @Override
    public void update(Resume r) {
        Object searchKey = getSearchKey(r.getUuid());
        if (isNotValid(searchKey)) {
            throw new NotExistStorageException(r.getUuid());
        } else {
            updateInStorage(r, searchKey);
        }
    }

    protected abstract void updateInStorage(Resume r, Object searchKey);

    @Override
    public Resume get(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (isNotValid(searchKey)) {
            throw new NotExistStorageException(uuid);
        } else return getInStorage(searchKey);
    }

    protected abstract Resume getInStorage(Object searchKey);

    @Override
    public void delete(String uuid) {
        Object searchKey =  getSearchKey(uuid);
        if (isNotValid(searchKey)) {
            throw new NotExistStorageException(uuid);
        } else deleteInStorage(searchKey);
    }

    protected abstract void deleteInStorage(Object searchKey);

    protected abstract boolean isNotValid(Object searchKey);

    protected abstract Object getSearchKey(String uuid);
}

