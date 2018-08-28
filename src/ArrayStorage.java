import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    int size = 0;

    void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    void save(Resume r) {
        if (isFound(r.uuid) == -1) {
            if (size < storage.length) {
                storage[size] = r;
                size++;
            } else {
                System.out.println("Storage is overflowed");
            }
        } else {
            System.out.println("Resume isn't unique");
        }
    }

    void update(Resume r) {
        int localIsFound = isFound(r.uuid);
        if (localIsFound != -1) {
            storage[isFound(r.uuid)] = r;
        } else {
            System.out.println("Resume doesn't exist");
        }
    }

    Resume get(String uuid) {
        int localIsFound = isFound(uuid);
        if (localIsFound != -1) {
            return storage[isFound(uuid)];
        } else {
            System.out.println("Resume doesn't exist");
        }
        return null;
    }

    void delete(String uuid) {
        int localIsFound = isFound(uuid);
        if (localIsFound != -1) {
            storage[isFound(uuid)] = storage[size - 1];
            storage[size - 1] = null;
            size--;
        } else {
            System.out.println("Resume doesn't exist");
        }
    }

    int isFound(String uuid) {
        int isFound = -1;
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equals(uuid)) {
                isFound = i;
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
