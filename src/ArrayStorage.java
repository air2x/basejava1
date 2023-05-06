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
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] == null) {
                storage[i] = r;
                break;
            }
        }
    }

    Resume get(String uuid) {
        for (Resume resume : storage) {
            if (uuid.equals(resume.uuid)) {
                return resume;
            } else {
                return null;
            }
        }
        return null;
    }

    void delete(String uuid) {
        for (int i = 0; i < storage.length; i++) {
            if ((storage[i].uuid).equals(uuid)) {
                storage[i] = null;
                break;
            }
        }
        int count = 0;
        for (Resume resume : storage) {
            if (resume != null) {
                count++;
            }
        }
        Resume[] tempStorage = new Resume[count];
        count = 0;
        int startNum = 0;
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] != null) {
                count++;
                if (count == 1) {
                    startNum = i;
                }
            } else {
                for (int j = 0; j < tempStorage.length; j++) {
                    if (tempStorage[j] == null) {
                        System.arraycopy(storage, startNum, tempStorage, j, count);
                        count = 0;
                        break;
                    }
                }
            }
        }
        storage = tempStorage;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        int count = 0;
        for (Resume resume : storage) {
            if (resume != null) {
                count++;
            }
        }
        return Arrays.copyOfRange(storage, 0, count);
    }

    int size() {
        int countResume = 0;
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] == null) {
                break;
            }
            countResume = i + 1;
        }
        return countResume;
    }
}
