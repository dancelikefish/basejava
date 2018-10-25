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
    public void saveInStorage(Resume r, int index) {
        resumeMap.put(r.getUuid(), r);
    }

    @Override
    public void updateInStorage(Resume r, int index) {
        resumeMap.replace(r.getUuid(), r, r);
    }

    @Override
    protected Resume getInStorage(String uuid, int index) {
        return resumeMap.get(uuid);
    }

    @Override
    protected int getIndex(String uuid) {
        int index = 0;
        for (Map.Entry<String, Resume> entry : resumeMap.entrySet()) {
            index++;
            if (entry.getKey().equals(uuid)) {
                return index;
            }
        }
        return -1;
    }

    @Override
    public void deleteInStorage(String uuid, int index) {
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
