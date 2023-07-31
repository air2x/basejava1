package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MapStorage extends AbstractStorage<String> {
    private final HashMap<String, Resume> storageMap = new HashMap<>();

    @Override
    protected boolean isExist(String searchKey) {
        return storageMap.containsKey(searchKey);
    }

    @Override
    protected String getSearchKey(String uuid) {
        return uuid;
    }

    @Override
    protected void doUpdate(String searchKey, Resume r) {
        storageMap.put(searchKey, r);
    }

    @Override
    protected void doSave(String searchKey, Resume r) {
        storageMap.put(searchKey, r);
    }

    @Override
    protected Resume doGet(String searchKey) {
        return storageMap.get(searchKey);
    }

    @Override
    protected void doDelete(String searchKey) {
        storageMap.remove(searchKey);
    }

    @Override
    public void clear() {
        storageMap.clear();
    }

    @Override
    public List<Resume> doGetAllSorted() {
        return new ArrayList<>(storageMap.values());
    }

    @Override
    public int size() {
        return storageMap.size();
    }
}
