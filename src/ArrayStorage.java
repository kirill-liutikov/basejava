import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];

    void clear() {
        Arrays.fill(storage, null);
    }

    void save(Resume r) {
        storage[size()] = r;
    }

    Resume get(String uuid) {
        for (int i = 0; i < size() ; i++) {
            if (storage[i].uuid.equals(uuid)) return storage[i];
        }
        return null;
    }

    void delete(String uuid) {
        for (int i = 0; i < size(); i++) {
            if (storage[i].uuid.equals(uuid)) {
                if (size() - i >= 0) System.arraycopy(storage, i + 1, storage, i, size() - i);
                break;
            }
        }
    }



    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] allResumes = new Resume[size()];
        if (size() >= 0) System.arraycopy(storage, 0, allResumes, 0, size());
        return allResumes;
    }

    int size() {
        int size = 0;
        for (Resume r : storage) {
            if (r != null)
                size++;
        }
        return size;
    }
}
