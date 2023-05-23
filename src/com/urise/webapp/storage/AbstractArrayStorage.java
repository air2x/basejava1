package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10000;
    protected static final int INDEX_MISSING_RESUME = -1;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public int size() {
        return size;
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void update(Resume r) {
        int indexFound = getIndex(r.getUuid());
        if (indexFound <= INDEX_MISSING_RESUME) {
            System.out.println("Resume not found");
        } else {
            storage[indexFound] = r;
        }
    }

    public void save(Resume r) {
    }

    public Resume get(String uuid) {
        return null;
    }

    public void delete(String uuid) {
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    protected abstract int getIndex(String uuid);

    protected void printResumeNotFound(String uuid) {
        System.out.println(uuid + " not found");
    }
}
