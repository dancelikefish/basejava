package javaOps.webapp.storage;

import javaOps.webapp.model.Resume;

public class SortedArrayStorage extends AbstractArrayStorage {
    @Override
    public void clear() {

    }

    @Override
    public void save(Resume r) {

    }

    @Override
    public void update(Resume r) {

    }

    @Override
    public void delete(String uuid) {

    }
    @Override
    protected int getFoundIndex(String uuid) {
        return 0;
    }

    @Override
    public Resume[] getAll() {
        return new Resume[0];
    }
}
