package ru.webapp.storage;

import ru.webapp.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class AbstractStorage implements Storage {
    protected List<Resume> resumeList = new ArrayList<>();
    protected HashMap<String, Resume> resumeMap = new HashMap<>();

    @Override
    public void clear() {
        clearInCollections();
    }
    protected abstract void clearInCollections();

    @Override
    public void save(Resume r) {

    }

    @Override
    public void update(Resume r) {

    }

    @Override
    public Resume get(String uuid) {
        return null;
    }

    @Override
    public void delete(String uuid) {

    }

    @Override
    public Resume[] getAll() {
        return new Resume[0];
    }

    @Override
    public int size() {
        return 0;
    }
}
