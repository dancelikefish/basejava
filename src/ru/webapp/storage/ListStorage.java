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
    protected void saveInStorage(Resume r, Resume searchKey) {
        resumeList.add(r);
    }

    @Override
    protected boolean isValid(Resume searchKey) {
        return searchKey.getSearchKey() < 0;
    }

    @Override
    protected void updateInStorage(Resume r, Resume searchKey) {
        resumeList.set(resumeList.indexOf(r), r);
    }

    @Override
    protected Resume getInStorage(String uuid, Resume searchKey) {
        int index = searchKey.getSearchKey();
        return resumeList.get(index);
    }

    @Override
    protected Resume getIndex(String uuid) {
        for (int i = 0; i < size(); i++) {
            if (uuid.equals(resumeList.get(i).getUuid())) {
                return new Resume(i);
            }
        }
        return new Resume(-1);
    }

    @Override
    protected void deleteInStorage(String uuid, Resume searchKey) {
        resumeList.remove(new Resume(uuid));
    }

    @Override
    public Resume[] getAll() {
        return resumeList.toArray(new Resume[0]);
    }

    @Override
    public int size() {
        return resumeList.size();
    }

}
