import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    void clear() {
        Arrays.fill(storage, 0, size(), null); // null in depends of a size()
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
            for (int i = 0; i < size(); i++) {
                if (storage[i].uuid.equals(uuid)) { //searching id and returning at once
                    return storage[i];
                }
            }
            return null;
    }

    void delete(String uuid) {
        for (int i = 0; i < size(); i++) {
            if (storage[i].uuid.equals(uuid)) {
                System.arraycopy(storage,i + 1, storage, i , size()); //edited to System.Arraycopy
                break;
            }
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
