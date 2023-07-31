package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage<Integer> {
    private final List<Resume> storageList = new ArrayList<>();

    @Override
    public void clear() {
        storageList.clear();
    }

    @Override
    protected boolean isExist(Integer searchKey) {
        return searchKey >= 0;
    }

    @Override
    protected Integer getSearchKey(String uuid) {
        for (int i = 0; i < size(); i++) {
            if (uuid.equals(storageList.get(i).getUuid())) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected void doUpdate(Integer searchKey, Resume r) {
        storageList.set(searchKey, r);
    }

    @Override
    protected void doSave(Integer searchKey, Resume r) {
        storageList.add(r);
    }

    @Override
    protected Resume doGet(Integer searchKey) {
        return storageList.get(searchKey);
    }

    @Override
    protected void doDelete(Integer searchKey) {
        storageList.remove(searchKey.intValue());
    }

    @Override
    public List<Resume> doGetAllSorted() {
        return storageList;
    }

    @Override
    public int size() {
        return storageList.size();
    }
}
