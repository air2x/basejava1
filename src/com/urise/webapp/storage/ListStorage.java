package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {
    private final List<Resume> storageList = new ArrayList<>();

    @Override
    public void clear() {
        storageList.clear();
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return (int) searchKey >= 0;
    }

    @Override
    protected Object getSearchKey(String uuid) {
        for (int i = 0; i < size(); i++) {
            if (uuid.equals(storageList.get(i).getUuid())) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected void doUpdate(Object searchKey, Resume r) {
        storageList.set((int) searchKey, r);
    }

    @Override
    protected void doSave(Object searchKey, Resume r) {
        storageList.add(r);
    }

    @Override
    protected Resume doGet(Object searchKey) {
        return storageList.get((int) searchKey);
    }

    @Override
    protected void doDelete(Object searchKey) {
        storageList.remove((int) searchKey);
    }

    @Override
    public Resume[] getAll() {
        return storageList.toArray(new Resume[0]);
    }

    @Override
    public int size() {
        return storageList.size();
    }
}
