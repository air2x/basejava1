package com.urise.webapp.storage;

import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    @Override
    public abstract void clear();

    @Override
    public abstract void update(Resume r) throws NotExistStorageException;

    @Override
    public abstract void save(Resume r) throws StorageException;

    @Override
    public abstract Resume get(String uuid) throws NotExistStorageException;

    @Override
    public abstract void delete(String uuid) throws NotExistStorageException;

    @Override
    public abstract Resume[] getAll();

    @Override
    public abstract int size();
}
