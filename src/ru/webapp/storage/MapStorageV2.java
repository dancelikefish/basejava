package ru.webapp.storage;

import ru.webapp.model.Resume;

import java.util.*;

public class MapStorageV2 extends AbstractStorage {

    protected Map<Resume, String> resumeMap = new LinkedHashMap<>();

    @Override
    public void clear() {
        resumeMap.clear();
    }

    @Override
    public void saveInStorage(Resume resume, Object searchKey) {
        resumeMap.put(resume, resume.getUuid());
    }

    @Override
    public void updateInStorage(Resume resume, Object searchKey) {
        resumeMap.replace((Resume) searchKey, resume.getUuid());
    }

    @Override
    protected Resume getFromStorage(Object searchKey) {
        return new Resume(resumeMap.get(searchKey));
    }

    @Override
    public void deleteFromStorage(Object searchKey) {
        resumeMap.remove(searchKey);
    }

    @Override
    public Collection<Resume> getCollection() {
        return resumeMap.keySet();
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
        for (Map.Entry<Resume, String> entry: resumeMap.entrySet()) {
            if (uuid.equals(entry.getValue())) {
                return entry.getKey();
            }
        }
        return null;
    }
}
