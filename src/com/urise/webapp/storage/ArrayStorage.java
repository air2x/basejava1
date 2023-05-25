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

    protected int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return INDEX_MISSING_RESUME;
    }
}
