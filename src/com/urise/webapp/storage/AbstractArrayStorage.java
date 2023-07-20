package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.util.Arrays;
import java.util.List;

public abstract class AbstractArrayStorage extends AbstractStorage {
    protected static final int STORAGE_LIMIT = 10000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public final int size() {
        return size;
    }

    public final void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    protected void doUpdate(Object searchKey, Resume r) {
        storage[(int) searchKey] = r;
    }

    protected void doSave(Object searchKey, Resume r) throws StorageException {
        if (size >= STORAGE_LIMIT) {
            throw new StorageException("Storage overflow", r.getUuid());
        } else {
            saveResume((int) searchKey, r);
        }
    }

    protected Resume doGet(Object searchKey) {
        return storage[(int) searchKey];
    }

    protected void doDelete(Object searchKey) {
        deleteResume((int) searchKey);
    }

    public final List<Resume> getAllSorted() {
        return Arrays.asList(Arrays.copyOf(storage, size));
    }

    protected boolean isExist(Object searchKey) {
        return (int) searchKey >= 0;
    }

    protected abstract void deleteResume(int index);

    protected abstract void saveResume(int index, Resume r);

    protected abstract Integer getSearchKey(String uuid);
}
