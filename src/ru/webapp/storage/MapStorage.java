package ru.webapp.storage;

import ru.webapp.exception.ExistStorageException;
import ru.webapp.exception.NotExistStorageException;
import ru.webapp.model.Resume;

public class MapStorage extends AbstractStorage {
    @Override
    public void clear() {
        resumeMap.entrySet().clear();
    }

    @Override
    public void save(Resume r) {
        if (resumeMap.containsKey(r.getUuid())) {
            throw new ExistStorageException(r.getUuid());
        } else resumeMap.put(r.getUuid(), r);
    }

    @Override
    public void update(Resume r) {
        if (resumeMap.containsKey(r.getUuid())) {
            resumeMap.replace(r.getUuid(), r, r);
        } else throw new NotExistStorageException(r.getUuid());
    }

    @Override
    public Resume get(String uuid) {
        if (resumeMap.containsKey(uuid)) {
            return resumeMap.get(uuid);
        } else throw new NotExistStorageException(uuid);
    }

    @Override
    public void delete(String uuid) {
        if (resumeMap.containsKey(uuid)) {
            resumeMap.remove(uuid);
        } else throw new NotExistStorageException(uuid);
    }

    @Override
    public Resume[] getAll() {
        return resumeMap.values().toArray(new Resume[0]);
    }

    @Override
    public int size() {
        return resumeMap.size();
    }
}
