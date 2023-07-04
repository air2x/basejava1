package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    @Override
    public void update(Resume r) throws NotExistStorageException {
        Object searchKey = getExistingSearchKey(r.getUuid());
        doUpdate(searchKey, r);
    }

    private Object getExistingSearchKey(String uuid) throws NotExistStorageException {
        Object searchKey = getSearchKey(uuid);
        if (!isExist(searchKey)) {
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }

    protected abstract boolean isExist(Object searchKey);

    protected abstract Object getSearchKey(String uuid);

    protected abstract void doUpdate(Object searchKey, Resume r);

    @Override
    public void save(Resume r) throws StorageException {
        Object searchKey = getNotExistingSearchKey(r.getUuid());
        doSave(searchKey, r);
    }

    private Object getNotExistingSearchKey(String uuid) throws ExistStorageException {
        Object searchKey = getSearchKey(uuid);
        if (isExist(searchKey)) {
            throw new ExistStorageException(uuid);
        }
        return searchKey;
    }

    protected abstract void doSave(Object searchKey, Resume r) throws StorageException;

    @Override
    public Resume get(String uuid) throws NotExistStorageException {
        Object searchKey = getExistingSearchKey(uuid);
        return doGet(searchKey);
    }

    protected abstract Resume doGet(Object searchKey);

    @Override
    public void delete(String uuid) throws NotExistStorageException {
        Object searchKey = getExistingSearchKey(uuid);
        doDelete(searchKey);
    }

    protected abstract void doDelete(Object searchKey);
}
