package javaOps.webapp.storage;

import javaOps.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 4;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void save(Resume r) {
        if (size == STORAGE_LIMIT) {
            System.out.println("Storage is overflowed");
        } else {
            int index = getIndex(r.getUuid());
            if (index < 0) {
                saveInArrays(r, index);
                size++;
            } else
                System.out.println("Resume " + r.getUuid() + " isn't unique");
        }
    }

    protected abstract void saveInArrays(Resume r, int index);

    public void update(Resume r) {
        int index = getIndex(r.getUuid());
        if (index >= 0) {
            storage[index] = r;
        } else {
            System.out.println("Resume " + r.getUuid() + " doesn't exist");
        }
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            System.out.println("Resume " + uuid + " doesn't exist");
            return null;
        }
        return storage[index];
    }

    protected abstract int getIndex(String uuid);

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index >= 0) {
            deleteInArrays(index);
            size--;
        } else {
            System.out.println("Resume " + uuid + " doesn't exist");
        }
    }

    protected abstract void deleteInArrays(int index);

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }
}
