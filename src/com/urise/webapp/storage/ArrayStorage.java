package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private static final int STORAGE_LIMIT = 10000;
    private static final int INDEX_MISSING_RESUME = -1;
    private final Resume[] storage = new Resume[STORAGE_LIMIT];
    private int size;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

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
        int indexFound = getIndex(uuid);
        if (indexFound != INDEX_MISSING_RESUME) {
            return storage[indexFound];
        } else {
            printResumeNotFound(uuid);
        }
        return null;
    }

    public void update(Resume r) {
        int indexFound = getIndex(r.getUuid());
        if (indexFound == INDEX_MISSING_RESUME) {
            System.out.println("Resume not found");
        } else {
            storage[indexFound] = r;
        }
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

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }

    private int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return INDEX_MISSING_RESUME;
    }

    private void printResumeNotFound(String uuid) {
        System.out.println(uuid + " not found");
    }
}
