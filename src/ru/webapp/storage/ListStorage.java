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
    protected void saveInStorage(Resume r, int index) {
        resumeList.add(r);
    }

    @Override
    protected void updateInStorage(Resume r, int index) {
        resumeList.set(resumeList.indexOf(r), r);
    }

    @Override
    protected Resume getInStorage(String uuid, int index) {
        return resumeList.get(index);
    }

    @Override
    protected int getIndex(String uuid) {
        for (int i = 0; i < size(); i++) {
            if (uuid.equals(resumeList.get(i).getUuid())) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected void deleteInStorage(String uuid, int index) {
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
