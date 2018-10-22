package ru.webapp.storage;

import ru.webapp.model.Resume;

import java.util.*;

public abstract class AbstractStorage implements Storage {

    protected Map<String, Resume> resumeMap = new HashMap<>();
    protected List<Resume> resumeList = new ArrayList<>();

    public void save(Resume r) {

    }

    public void update(Resume r) {

    }


    public Resume get(String uuid) {
        return null;
    }

    public void delete(String uuid) {

    }

    public Resume[] getAll() {
        return null;
    }


    public int size() {
        return 0;
    }


}

