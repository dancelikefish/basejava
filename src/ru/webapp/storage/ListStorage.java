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
    protected void saveInStorage(Resume r, Object searchKey) {
        resumeList.add(r);
    }

    @Override
    protected void updateInStorage(Resume r, Object searchKey) {
        resumeList.set((Integer) searchKey, r);
    }

    @Override
    protected Resume getInStorage(Object searchKey) {
        return resumeList.get((Integer) searchKey);
    }

    @Override
    protected void deleteInStorage(Object searchKey) {
        resumeList.remove(((Integer) searchKey).intValue());
    }

    @Override
    public Resume[] getAll() {
        return resumeList.toArray(new Resume[resumeList.size()]);
    }

    @Override
    public int size() {
        return resumeList.size();
    }

    @Override
    protected boolean isNotValid(Object searchKey) {
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
