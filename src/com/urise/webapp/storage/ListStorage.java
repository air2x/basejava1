package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {
    List<Resume> storageList = new ArrayList<>();

    @Override
    public void clear() {
        storageList.clear();
    }

    @Override
    public void update(Resume r) throws NotExistStorageException {
        int index = storageList.indexOf(r);
        if (index < 0) {
            throw new NotExistStorageException(r.getUuid());
        } else {
            storageList.set(index, r);
        }
    }

    @Override
    public void save(Resume r) throws ExistStorageException {
        int index = storageList.indexOf(r);
        if (index >= 0) {
            throw new ExistStorageException(r.getUuid());
        } else {
            storageList.add(r);
        }
    }

    @Override
    public Resume get(String uuid) throws NotExistStorageException {
        int index = getIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        } else {
            return storageList.get(index);
        }
    }

    @Override
    public void delete(String uuid) throws NotExistStorageException {
        int index = getIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        } else {
            storageList.remove(index);
        }
    }

    @Override
    public Resume[] getAll() {
        return storageList.toArray(new Resume[0]);
    }

    @Override
    public int size() {
        return storageList.size();
    }

    protected int getIndex(String uuid) {
        Resume[] resumes = storageList.toArray(new Resume[0]);
        for (int i = 0; i < resumes.length; i++) {
            if (uuid.equals(resumes[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }
}
