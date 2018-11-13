package ru.webapp.storage;

import ru.webapp.model.Resume;

import java.util.*;

public class MapUuidStorage extends AbstractStorage<String> {

    protected Map<String, Resume> resumeMap = new LinkedHashMap<>();

    @Override
    public void clear() {
        resumeMap.clear();
    }

    @Override
    public void saveInStorage(Resume resume, String searchKey) {
        resumeMap.put(resume.getUuid(), resume);
    }

    @Override
    public void updateInStorage(Resume resume, String searchKey) {
        resumeMap.replace(searchKey, resume);
    }

    @Override
    protected Resume getFromStorage(String searchKey) {
        String key = searchKey;
        return resumeMap.get(key);
    }

    @Override
    public void deleteFromStorage(String searchKey) {
        String key = searchKey;
        resumeMap.remove(key);
    }

    @Override
    public List<Resume> getCopyList() {
        return new ArrayList<>(resumeMap.values());
    }

    @Override
    public int size() {
        return resumeMap.size();
    }

    @Override
    protected boolean isValid(String searchKey) {
        return !resumeMap.containsKey(searchKey);
    }

    @Override
    protected String getSearchKey(String uuid) {
        return uuid;
    }
}
