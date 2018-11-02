package ru.webapp.storage;

import ru.webapp.model.Resume;

import java.util.*;

import static ru.webapp.model.Resume.FULLNAME_COMPARATOR;

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
    public List<Resume> getAllSorted() {
        List<Resume> resumes = new ArrayList<>(resumeMap.values());
        resumes.sort(FULLNAME_COMPARATOR);
        return resumes;
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
