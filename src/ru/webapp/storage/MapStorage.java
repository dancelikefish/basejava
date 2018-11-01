package ru.webapp.storage;

import ru.webapp.model.Resume;

import java.util.LinkedHashMap;
import java.util.Map;

public class MapStorage extends AbstractStorage {

    protected Map<String, Resume> resumeMap = new LinkedHashMap<>();

    @Override
    public void clear() {
        resumeMap.clear();
    }

    @Override
    public void saveInStorage(Resume r, Object searchKey) {
        resumeMap.put(r.getUuid(), r);
    }

    @Override
    public void updateInStorage(Resume r, Object searchKey) {
        resumeMap.replace((String) searchKey, r);
    }

    @Override
    protected Resume getInStorage(Object searchKey) {
        String key = (String) searchKey;
        return resumeMap.get(key);
    }

    @Override
    public void deleteInStorage(Object searchKey) {
        String key = (String) searchKey;
        resumeMap.remove(key);
    }

    @Override
    public Resume[] getAll() {
        return resumeMap.values().toArray(new Resume[0]);
    }

    @Override
    public int size() {
        return resumeMap.size();
    }

    @Override
    protected boolean isNotValid(Object searchKey) {
        return !resumeMap.containsKey(searchKey);
    }

    @Override
    protected Object getSearchKey(String uuid) {
        return uuid;
    }
}
