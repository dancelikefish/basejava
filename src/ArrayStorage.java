import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    void clear() {
        Arrays.fill(storage, null); // null fillings
    }

    void save(Resume r) {
        boolean isEquals = false;
        for (int i = 0; i < size(); i++) {
            if (storage[i].uuid.equals(r.uuid)) { //saving in depends of size() func without nulls and duplicates
                isEquals = true;
            }
        }
        if (!isEquals) {
            storage[size()] = r;
        }
    }

    Resume get(String uuid) {
        Resume savedValue = null;
            for (int i = 0; i < size(); i++) {
                if (storage[i].uuid.equals(uuid)) { //searching of id
                    savedValue = storage[i];
                }
            }
        return savedValue;
    }

    void delete(String uuid) {
        boolean isExistedUuid = false;
        int indexOfDeletedFile = 0;
        for (int i = 0; i < size(); i++) {
            if (storage[i].uuid.equals(uuid)) {
                isExistedUuid = true;
                storage[i] = null;
                indexOfDeletedFile = i;
            }
        }
        if (isExistedUuid) {
            for (int i = indexOfDeletedFile; i < storage.length - 1; i++) { //shifting mass after deleting a value
                storage[i] = storage[i + 1];
            }
            storage = Arrays.copyOf(storage, storage.length - 1); // new mass
        }
    }
    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return Arrays.copyOf(storage, size());
    }

    int size() {
        int sizeCounter = 0;
        for (Resume resume : storage) {
            if (resume != null ) {  // finding not null values, and setting size
                sizeCounter++;
            }
        }
        return sizeCounter;
    }
}
