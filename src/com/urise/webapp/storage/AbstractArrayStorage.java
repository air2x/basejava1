package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.util.Arrays;
import java.util.List;

public abstract class AbstractArrayStorage extends AbstractStorage<Integer> {
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

    protected void doUpdate(Integer searchKey, Resume r) {
        storage[searchKey] = r;
    }

    protected void doSave(Integer searchKey, Resume r) throws StorageException {
        if (size >= STORAGE_LIMIT) {
            throw new StorageException("Storage overflow", r.getUuid());
        } else {
            saveResume((int) searchKey, r);
        }
    }

    protected Resume doGet(Integer searchKey) {
        return storage[searchKey];
    }

    protected void doDelete(Integer searchKey) {
        deleteResume(searchKey);
    }

    public final List<Resume> doGetAllSorted() {
        return Arrays.asList(Arrays.copyOf(storage, size));
    }

    protected boolean isExist(Integer searchKey) {
        return searchKey >= 0;
    }

    protected abstract void deleteResume(int index);

    protected abstract void saveResume(int index, Resume r);

    protected abstract Integer getSearchKey(String uuid);
}
