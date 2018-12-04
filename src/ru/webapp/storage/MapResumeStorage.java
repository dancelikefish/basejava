package ru.webapp.storage;

import ru.webapp.model.Resume;

import java.util.*;

public class MapResumeStorage extends AbstractStorage<Resume> {

    protected Map<String, Resume> resumeMap = new LinkedHashMap<>();

    @Override
    public void clear() {
        resumeMap.clear();
    }

    @Override
    public void saveInStorage(Resume resume, Resume searchKey) {
        resumeMap.put(resume.getUuid(), resume);
    }

    @Override
    public void updateInStorage(Resume resume, Resume searchKey) {
        resumeMap.replace(resume.getUuid(), searchKey);
    }

    @Override
    protected Resume getFromStorage(Resume searchKey) {
        return searchKey;
    }

    @Override
    public void deleteFromStorage(Resume searchKey) {
        resumeMap.remove(searchKey.getUuid());
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
    protected boolean isValid(Resume searchKey) {
        return !resumeMap.containsValue(searchKey);
    }

    @Override
    protected Resume getSearchKey(String uuid) {
        return resumeMap.get(uuid);
    }
}
