package ru.webapp.storage;

import ru.webapp.exception.ExistStorageException;
import ru.webapp.exception.NotExistStorageException;
import ru.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    @Override
    public void save(Resume r) {
        Resume searchKey = getIndex(r.getUuid());
        if (!isValid(searchKey)) {
            throw new ExistStorageException(r.getUuid());
        } else {
            saveInStorage(r, searchKey);
        }
    }

    protected abstract boolean isValid(Resume searchKey);

    protected abstract void saveInStorage(Resume r, Resume searchKey);

    @Override
    public void update(Resume r) {
        Resume searchKey = getIndex(r.getUuid());
        if (isValid(searchKey)) {
            throw new NotExistStorageException(r.getUuid());
        } else {
            updateInStorage(r, searchKey);
        }
    }

    protected abstract void updateInStorage(Resume r, Resume searchKey);

    @Override
    public Resume get(String uuid) {
        Resume searchKey = getIndex(uuid);
        if (isValid(searchKey)) {
            throw new NotExistStorageException(uuid);
        } else return getInStorage(uuid, searchKey);
    }

    protected abstract Resume getInStorage(String uuid, Resume SearchKey);

    protected abstract Resume getIndex(String uuid);

    @Override
    public void delete(String uuid) {
        Resume searchKey = getIndex(uuid);
        if (isValid(searchKey)) {
            throw new NotExistStorageException(uuid);
        } else deleteInStorage(uuid, searchKey);
    }

    protected abstract void deleteInStorage(String uuid, Resume searchKey);


}

