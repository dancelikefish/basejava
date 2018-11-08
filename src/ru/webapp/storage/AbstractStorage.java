package ru.webapp.storage;

import ru.webapp.exception.ExistStorageException;
import ru.webapp.exception.NotExistStorageException;
import ru.webapp.model.Resume;

import java.util.Comparator;
import java.util.List;

public abstract class AbstractStorage implements Storage {

    protected abstract void saveInStorage(Resume resume, Object searchKey);

    protected abstract void updateInStorage(Resume resume, Object searchKey);

    protected abstract Resume getFromStorage(Object searchKey);

    protected abstract void deleteFromStorage(Object searchKey);

    protected abstract boolean isValid(Object searchKey);

    protected abstract Object getSearchKey(String uuid);

    @Override
    public void save(Resume resume) {
        Object searchKey = getNotValidSearchKey(resume.getUuid());
        saveInStorage(resume, searchKey);
    }

    @Override
    public void update(Resume resume) {
        Object searchKey = getValidSearchKey(resume.getUuid());
        updateInStorage(resume, searchKey);
    }

    @Override
    public Resume get(String uuid) {
        Object searchKey = getValidSearchKey(uuid);
        return getFromStorage(searchKey);
    }

    @Override
    public void delete(String uuid) {
        Object searchKey = getValidSearchKey(uuid);
        deleteFromStorage(searchKey);
    }

    @Override
    public List<Resume> getAllSorted() {
        List<Resume> resumes = getList();
        resumes.sort(FULLNAME_COMPARATOR);
        return resumes;
    }

    protected abstract List<Resume> getList();

    public static final Comparator<Resume> FULLNAME_COMPARATOR = (o1, o2) -> o1.getFullName().compareTo(o2.getFullName())== 0 ?
            o1.getUuid().compareTo(o2.getUuid()) :
            o1.getFullName().compareTo(o2.getFullName());

    private Object getNotValidSearchKey(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (!isValid(searchKey)) {
            throw new ExistStorageException(uuid);
        }
        return searchKey;
    }

    private Object getValidSearchKey(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (isValid(searchKey)) {
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }
}

