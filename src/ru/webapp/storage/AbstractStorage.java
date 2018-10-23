package ru.webapp.storage;

import ru.webapp.exception.ExistStorageException;
import ru.webapp.exception.NotExistStorageException;
import ru.webapp.exception.StorageException;
import ru.webapp.model.Resume;

import java.util.*;

import static ru.webapp.storage.AbstractArrayStorage.STORAGE_LIMIT;

public abstract class AbstractStorage implements Storage {

    protected Map<String, Resume> resumeMap = new LinkedHashMap<>();
    protected List<Resume> resumeList = new ArrayList<>();
    protected int size = 0;

    @Override
    public void save(Resume r) {
        if (size == STORAGE_LIMIT) {
            throw new StorageException("Storage is overflowed", r.getUuid());
        } else {
            int index = getIndex(r.getUuid());
            if (index < 0) {
                saveInStorage(r, index);
                size++;
            } else
                throw new ExistStorageException(r.getUuid());
        }
    }
    protected abstract void saveInStorage(Resume r, int index);

    @Override
    public void update(Resume r) {
        int index = getIndex(r.getUuid());
        if (index < 0) {
            throw new NotExistStorageException(r.getUuid());
        } else {
            updateInStorage(r, index);
        }
    }
    protected abstract void updateInStorage(Resume r, int index);

    @Override
    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        }else return getInStorage(uuid, index);
    }

    protected abstract Resume getInStorage(String uuid, int index);

    protected abstract int getIndex(String uuid);

    @Override
    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        } else deleteInStorage(uuid, index);
    }

    protected abstract void deleteInStorage(String uuid, int index);



}

