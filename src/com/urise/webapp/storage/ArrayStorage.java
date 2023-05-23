package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    public void save(Resume r) {
        int indexFound = getIndex(r.getUuid());
        if (size >= STORAGE_LIMIT) {
            System.out.println("The resume database is full");
        } else if (indexFound != INDEX_MISSING_RESUME) {
            System.out.println("There is already such a resume");
        } else {
            storage[size] = r;
            size++;
        }
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index == INDEX_MISSING_RESUME) {
            System.out.println("Resume " + uuid + " not exist");
            return null;
        }
        return storage[index];
    }

    public void delete(String uuid) {
        int indexFound = getIndex(uuid);
        if (indexFound != INDEX_MISSING_RESUME) {
            System.arraycopy(storage, indexFound + 1, storage, indexFound, size - indexFound - 1);
            storage[size - 1] = null;
            size--;
        } else {
            printResumeNotFound(uuid);
        }
    }

    protected int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return INDEX_MISSING_RESUME;
    }

}
