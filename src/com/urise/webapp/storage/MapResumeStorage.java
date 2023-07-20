package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class MapResumeStorage extends AbstractStorage {
    private final HashMap<String, Resume> storageMapResume = new HashMap<>();

    @Override
    protected boolean isExist(Object resume) {
        return resume != null;
    }

    @Override
    protected Object getSearchKey(String uuid) {
        return storageMapResume.get(uuid);
    }

    @Override
    protected void doUpdate(Object resume, Resume r) {
        storageMapResume.put(r.getUuid(), r);
    }

    @Override
    protected void doSave(Object searchKey, Resume r) {
        storageMapResume.put(r.getUuid(), r);
    }

    @Override
    protected Resume doGet(Object r) {
        return (Resume) r;
    }

    @Override
    protected void doDelete(Object resume) {
        storageMapResume.remove(((Resume) resume).getUuid());
    }

    @Override
    public void clear() {
        storageMapResume.clear();
    }

    @Override
    public List<Resume> getAllSorted() {
        List<Resume> mapSort = new ArrayList<>(storageMapResume.values());
        Collections.sort(mapSort);
        return mapSort;
    }

    @Override
    public int size() {
        return storageMapResume.size();
    }
}
