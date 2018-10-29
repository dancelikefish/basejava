package ru.webapp.storage;

import ru.webapp.exception.ExistStorageException;
import ru.webapp.exception.NotExistStorageException;
import ru.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    @Override
    public void save(Resume r) {
        Object searchKey = getIndex(r.getUuid());
        if (!isValid(searchKey)) {
            throw new ExistStorageException(r.getUuid());
        } else {
            saveInStorage(r, searchKey);
        }
    }

    protected abstract boolean isValid(Object searchKey);

    protected abstract void saveInStorage(Resume r, Object searchKey);

    @Override
    public void update(Resume r) {
        Object searchKey = getIndex(r.getUuid());
        if (isValid(searchKey)) {
            throw new NotExistStorageException(r.getUuid());
        } else {
            updateInStorage(r, searchKey);
        }
    }

    protected abstract void updateInStorage(Resume r, Object searchKey);

    @Override
    public Resume get(String uuid) {
        Object searchKey = getIndex(uuid);
        if (isValid(searchKey)) {
            throw new NotExistStorageException(uuid);
        } else return getInStorage(uuid, searchKey);
    }

    protected abstract Resume getInStorage(String uuid, Object SearchKey);

    protected abstract Object getIndex(String uuid);

    @Override
    public void delete(String uuid) {
        Object searchKey =  getIndex(uuid);
        if (isValid(searchKey)) {
            throw new NotExistStorageException(uuid);
        } else deleteInStorage(uuid, searchKey);
    }

    protected abstract void deleteInStorage(String uuid, Object searchKey);


}

