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
        resumeMap.replace(r.getUuid(), r, r);
    }

    @Override
    protected Resume getInStorage(String uuid, Object searchKey) {
        return resumeMap.get(uuid);
    }

    @Override
    public void deleteInStorage(String uuid, Object searchKey) {
        resumeMap.remove(uuid);
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
        return searchKey == null;
    }

    @Override
    protected Object getIndex(String uuid) {
        if (resumeMap.containsKey(uuid)) {
            return resumeMap.get(uuid);
        }
        return null;
    }
}
