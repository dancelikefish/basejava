package ru.webapp.storage;

import ru.webapp.model.Resume;

import java.util.*;

public class MapResumeStorage extends AbstractStorage {

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
        resumeMap.replace(resume.getUuid(), (Resume) searchKey);
    }

    @Override
    protected Resume getFromStorage(Object searchKey) {
        return (Resume) searchKey;
    }

    @Override
    public void deleteFromStorage(Object searchKey) {
        resumeMap.remove(searchKey.toString());
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
        return !resumeMap.containsValue(searchKey);
    }

    @Override
    protected Object getSearchKey(String uuid) {
        return resumeMap.get(uuid);
    }
}
