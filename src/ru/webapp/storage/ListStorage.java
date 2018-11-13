package ru.webapp.storage;

import ru.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {

    protected List<Resume> resumeList = new ArrayList<>();

    @Override
    public void clear() {
        resumeList.clear();
    }

    @Override
    protected void saveInStorage(Resume resume, Object searchKey) {
        resumeList.add(resume);
    }

    @Override
    protected void updateInStorage(Resume resume, Object searchKey) {
        resumeList.set((Integer) searchKey, resume);
    }

    @Override
    protected Resume getFromStorage(Object searchKey) {
        return resumeList.get((Integer) searchKey);
    }

    @Override
    protected void deleteFromStorage(Object searchKey) {
        resumeList.remove(((Integer) searchKey).intValue());
    }

    @Override
    public List<Resume> getCopyList() {
        return new ArrayList<>(resumeList);
    }

    @Override
    public int size() {
        return resumeList.size();
    }

    @Override
    protected boolean isValid(Object searchKey) {
        return (Integer) searchKey < 0;
    }

    @Override
    protected Object getSearchKey(String uuid) {
        for (int i = 0; i < size(); i++) {
            if (uuid.equals(resumeList.get(i).getUuid())) {
                return i;
            }
        }
        return -1;
    }
}
