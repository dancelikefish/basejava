package ru.webapp.storage;

import ru.webapp.model.Resume;

import java.util.Arrays;
import java.util.Comparator;

public class SortedArrayStorage extends AbstractArrayStorage {

    private static final Comparator<Resume> RESUME_COMPARATOR = Comparator.comparing(Resume::getFullName);

    @Override
    protected void saveInArray(Resume resume, Object binaryValue) {
        int index = (Integer) binaryValue;
        index = -index - 1;
        System.arraycopy(storage, index, storage, index + 1, size - index);
        storage[index] = resume;
    }

    @Override
    protected void deleteInArray(int binaryValue) {
        System.arraycopy(storage, binaryValue + 1, storage, binaryValue, size - binaryValue - 1);
    }

    @Override
    protected Object getSearchKey(String uuid) {
        Resume searchKey = new Resume(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey, RESUME_COMPARATOR);
    }
}
