package ru.webapp.storage;

import ru.webapp.exception.ExistStorageException;
import ru.webapp.exception.NotExistStorageException;
import ru.webapp.model.Resume;

public class ListStorage extends AbstractStorage {
    @Override
    public void clear() {
        resumeList.clear();
    }

    @Override
    public void save(Resume r) {
        if (resumeList.contains(r)) {
            throw new ExistStorageException(r.getUuid());
        } else resumeList.add(r);
    }

    @Override
    public void update(Resume r) {
        if (resumeList.contains(r)) {
            resumeList.set(resumeList.indexOf(r), r);
        } else throw new NotExistStorageException(r.getUuid());
    }

    @Override
    public Resume get(String uuid) {
        if (resumeList.contains(new Resume(uuid))) {
            return resumeList.get(resumeList.indexOf(new Resume(uuid)));
        } else throw new NotExistStorageException(uuid);
    }

    @Override
    public void delete(String uuid) {
        if (resumeList.contains(new Resume(uuid))) {
            resumeList.remove(new Resume(uuid));
        } else throw new NotExistStorageException(uuid);
    }

    @Override
    public Resume[] getAll() {
        return resumeList.toArray(new Resume[0]);
    }

    @Override
    public int size() {
        return resumeList.size();
    }
}
