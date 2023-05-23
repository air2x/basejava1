package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    public Resume get(String uuid) {
        int indexFound = getIndex(uuid);
        if (indexFound != INDEX_MISSING_RESUME) {
            return storage[indexFound];
        } else {
            printResumeNotFound(uuid);
        }
        return null;
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
