package ru.webapp.storage;

import ru.webapp.model.Resume;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static ru.webapp.model.Resume.FULLNAME_COMPARATOR;

public class MapStorageV2 extends AbstractStorage {

    protected Map<Resume, String> resumeMap = new LinkedHashMap<>();

    @Override
    public void clear() {
        resumeMap.clear();
    }

    @Override
    public void saveInStorage(Resume r, Object searchKey) {
        resumeMap.put(r, r.getUuid());
    }

    @Override
    public void updateInStorage(Resume r, Object searchKey) {
        resumeMap.replace((Resume) searchKey, r.getUuid());
    }

    @Override
    protected Resume getInStorage(Object searchKey) {
        return new Resume(resumeMap.get(searchKey));
    }

    @Override
    public void deleteInStorage(Object searchKey) {
        resumeMap.remove(searchKey);
    }

    @Override
    public List<Resume> getAllSorted() {
        List<Resume> resumes = new ArrayList<>(resumeMap.keySet());
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
        for (Map.Entry<Resume, String> entry: resumeMap.entrySet()) {
            if (uuid.equals(entry.getValue())) {
                return entry.getKey();
            }
        }
        return null;
    }
}
