package ru.webapp.storage;

import ru.webapp.exception.ExistStorageException;
import ru.webapp.exception.NotExistStorageException;
import ru.webapp.model.Resume;

import java.util.Comparator;
import java.util.List;

public abstract class AbstractStorage<SK> implements Storage {

    protected static final Comparator<Resume> FULLNAME_COMPARATOR = Comparator.comparing(Resume::getFullName).thenComparing(Resume::getUuid);

    protected abstract void saveInStorage(Resume resume, SK searchKey);

    protected abstract void updateInStorage(Resume resume, SK searchKey);

    protected abstract Resume getFromStorage(SK searchKey);

    protected abstract void deleteFromStorage(SK searchKey);

    protected abstract boolean isValid(SK searchKey);

    protected abstract SK getSearchKey(String uuid);

    @Override
    public void save(Resume resume) {
        SK searchKey = getNotValidSearchKey(resume.getUuid());
        saveInStorage(resume, searchKey);
    }

    @Override
    public void update(Resume resume) {
        SK searchKey = getValidSearchKey(resume.getUuid());
        updateInStorage(resume, searchKey);
    }

    @Override
    public Resume get(String uuid) {
        SK searchKey = getValidSearchKey(uuid);
        return getFromStorage(searchKey);
    }

    @Override
    public void delete(String uuid) {
        SK searchKey = getValidSearchKey(uuid);
        deleteFromStorage(searchKey);
    }

    @Override
    public List<Resume> getAllSorted() {
        List<Resume> resumes = getCopyList();
        resumes.sort(FULLNAME_COMPARATOR);
        return resumes;
    }

    protected abstract List<Resume> getCopyList();

    private SK getNotValidSearchKey(String uuid) {
        SK searchKey = getSearchKey(uuid);
        if (!isValid(searchKey)) {
            throw new ExistStorageException(uuid);
        }
        return searchKey;
    }

    private SK getValidSearchKey(String uuid) {
        SK searchKey = getSearchKey(uuid);
        if (isValid(searchKey)) {
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }
}

