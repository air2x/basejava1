package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;
import java.util.List;

public class ListStorage extends AbstractStorage {
    List<Resume> storageList = Arrays.asList(storage);


    @Override
    public void clear() {
        storageList.clear();
    }

    @Override
    public void update(Resume r) {
        storageList.set(storageList.indexOf(r), r);
    }

    @Override
    public void save(Resume r) {
        storageList.add(r);
    }

    @Override
    public Resume get(String uuid) {
        return storageList.get(getIndex(uuid));
    }

    @Override
    public void delete(String uuid) {
        storageList.remove(getIndex(uuid));
    }

    @Override
    public Resume[] getAll() {
        return storage;
    }

    @Override
    public int size() {
        return storageList.size();
    }
}
