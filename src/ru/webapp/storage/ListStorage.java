package ru.webapp.storage;

import ru.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage<Integer> {

    protected List<Resume> resumeList = new ArrayList<>();

    @Override
    public void clear() {
        resumeList.clear();
    }

    @Override
    protected void saveInStorage(Resume resume, Integer searchKey) {
        resumeList.add(resume);
    }

    @Override
    protected void updateInStorage(Resume resume, Integer searchKey) {
        resumeList.set(searchKey, resume);
    }

    @Override
    protected Resume getFromStorage(Integer searchKey) {
        return resumeList.get(searchKey);
    }

    @Override
    protected void deleteFromStorage(Integer searchKey) {
        resumeList.remove((searchKey).intValue());
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
    protected boolean isValid(Integer searchKey) {
        return searchKey < 0;
    }

    @Override
    protected Integer getSearchKey(String uuid) {
        for (int i = 0; i < size(); i++) {
            if (uuid.equals(resumeList.get(i).getUuid())) {
                return i;
            }
        }
        return -1;
    }
}
