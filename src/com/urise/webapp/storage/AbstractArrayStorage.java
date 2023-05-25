package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10000;
    protected static final int INDEX_MISSING_RESUME = -1;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public final int size() {
        return size;
    }

    public final void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public final void update(Resume r) {
        int indexFound = getIndex(r.getUuid());
        if (indexFound <= INDEX_MISSING_RESUME) {
            System.out.println("Resume not found");
        } else {
            storage[indexFound] = r;
        }
    }

    public abstract void save(Resume r);

    public final Resume get(String uuid) {
        int indexFound = getIndex(uuid);
        if (indexFound <= INDEX_MISSING_RESUME) {
            System.out.println("Resume " + uuid + " not exist");
            return null;
        }
        return storage[indexFound];
    }

    public final void delete(String uuid) {
        int indexFound = getIndex(uuid);
        if (indexFound > INDEX_MISSING_RESUME) {
            System.arraycopy(storage, indexFound + 1, storage, indexFound, size - indexFound - 1);
            storage[size - 1] = null;
            size--;
        } else {
            printResumeNotFound(uuid);
        }
    }

    public final Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    protected abstract int getIndex(String uuid);

    protected final void printResumeNotFound(String uuid) {
        System.out.println(uuid + " not found");
    }
}
