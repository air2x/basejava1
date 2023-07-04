package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.HashMap;
import java.util.Map;

public class MapStorage extends AbstractStorage {
    private final HashMap<String, Resume> storageMap = new HashMap<>();

    @Override
    protected boolean isExist(Object searchKey) {
        return storageMap.containsKey((String) searchKey);
    }

    @Override
    protected Object getSearchKey(String uuid) {
        return uuid;
    }

    @Override
    protected void doUpdate(Object searchKey, Resume r) {
        storageMap.put((String) searchKey, r);
    }

    @Override
    protected void doSave(Object searchKey, Resume r) {
        storageMap.put((String) searchKey, r);
    }

    @Override
    protected Resume doGet(Object searchKey) {
        return storageMap.get((String) searchKey);
    }

    @Override
    protected void doDelete(Object searchKey) {
        storageMap.remove((String) searchKey);
    }

    @Override
    public void clear() {
        storageMap.clear();
    }

    @Override
    public Resume[] getAll() {
        return storageMap.values().toArray(new Resume[0]);
    }

    @Override
    public int size() {
        return storageMap.size();
    }
}
