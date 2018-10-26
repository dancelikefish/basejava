package ru.webapp.storage;

import ru.webapp.model.Resume;

import java.util.LinkedHashMap;
import java.util.Map;

public class MapStorage extends AbstractStorage {

    protected Map<String, Resume> resumeMap = new LinkedHashMap<>();

    @Override
    public void clear() {
        resumeMap.entrySet().clear();
    }

    @Override
    public void saveInStorage(Resume r, Resume index) {
        resumeMap.put(r.getUuid(), r);
    }

    @Override
    protected boolean isValid(Resume searchKey) {
        return searchKey == null;
    }

    @Override
    public void updateInStorage(Resume r, Resume searchKey) {
        resumeMap.replace(r.getUuid(), r, r);
    }

    @Override
    protected Resume getInStorage(String uuid, Resume searchKey) {
        return resumeMap.get(uuid);
    }

    @Override
    protected Resume getIndex(String uuid) {
        for (Map.Entry<String, Resume> entry : resumeMap.entrySet()) {
            if (uuid.equals(entry.getKey())) {
                return entry.getValue();
            }
        }
        return null;
    }

    @Override
    public void deleteInStorage(String uuid, Resume searchKey) {
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

}
