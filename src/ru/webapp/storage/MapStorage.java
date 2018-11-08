package ru.webapp.storage;

import ru.webapp.model.Resume;

import java.util.*;

public class MapStorage extends AbstractStorage {

    protected Map<String, Resume> resumeMap = new LinkedHashMap<>();

    @Override
    public void clear() {
        resumeMap.clear();
    }

    @Override
    public void saveInStorage(Resume resume, Object searchKey) {
        resumeMap.put(resume.getUuid(), resume);
    }

    @Override
    public void updateInStorage(Resume resume, Object searchKey) {
        resumeMap.replace((String) searchKey, resume);
    }

    @Override
    protected Resume getFromStorage(Object searchKey) {
        String key = (String) searchKey;
        return resumeMap.get(key);
    }

    @Override
    public void deleteFromStorage(Object searchKey) {
        String key = (String) searchKey;
        resumeMap.remove(key);
    }

    @Override
    public List<Resume> getList() {
        return new ArrayList<>(resumeMap.values());
    }

    @Override
    public int size() {
        return resumeMap.size();
    }

    @Override
    protected boolean isValid(Object searchKey) {
        return !resumeMap.containsKey(searchKey);
    }

    @Override
    protected Object getSearchKey(String uuid) {
        return uuid;
    }
}
