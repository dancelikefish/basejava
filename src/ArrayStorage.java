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
        boolean isEquals = false;
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equals(r.uuid) && size < storage.length) {
                isEquals = true;
            }
        }
        if (!isEquals) {
            storage[size] = r;
            size++;
        } else {
            System.out.println("Resume isn't unique or storage is overflowed ");
        }
    }

    void update(Resume r) {
        int indexForUpdate = -1;
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equals(r.uuid)) {
                indexForUpdate = i;
            } else if (size - 1 == i && indexForUpdate == -1) {
                System.out.println("Resume doesn't exist");
                break;
            }
        }
        if (indexForUpdate != -1) {
            storage[indexForUpdate].uuid = r.uuid;
        }
    }

    Resume get(String uuid) {
        boolean isFound = false;
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equals(uuid)) { //searching id and returning at once
                isFound = true;
                return storage[i];
            } else if (i == size - 1 && !isFound) {
                System.out.println("Resume doesn't exist");
            }
        }
        return null;
    }

    void delete(String uuid) {
        boolean isExist = false;
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equals(uuid)) {
                System.arraycopy(storage, i + 1, storage, i, size);
                size--;
                isExist = true;
                break;
            } else if (i == size - 1 && !isExist) {
                System.out.println("Resume doesn't exist");
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }
}
