import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    int size = 0;
    int searchingIndex = 0;

    void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    void save(Resume r) {
        if (!isFound(r.uuid) && size < storage.length && r.uuid != null) {
            storage[size] = r;
            size++;
        } else {
            System.out.println("Resume isn't unique, or storage is overflowed, or uuid = null");
        }
    }

    void update(Resume r) {
        if (isFound(r.uuid)) {
            storage[searchingIndex].uuid = r.uuid;
        } else {
            System.out.println("Resume doesn't exist");
        }
    }

    Resume get(String uuid) {
        if (isFound(uuid)) {
            return storage[searchingIndex];
        } else {
            System.out.println("Resume doesn't exist");
        }
        return null;
    }

    void delete(String uuid) {
        if (isFound(uuid)) {
            storage[searchingIndex] = storage[searchingIndex + 1];
            storage[searchingIndex + 1] = null;
            size--;
        } else {
            System.out.println("Resume doesn't exist");
        }
    }

    boolean isFound(String uuid) {
        boolean isFound = false;
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equals(uuid)) {
                isFound = true;
                searchingIndex = i;
            }
        }
        return isFound;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }
}
