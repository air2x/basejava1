package com.urise.webapp.storage;

import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

public interface Storage {

    void clear();

    void update(Resume r) throws NotExistStorageException;

    void save(Resume r) throws StorageException;

    Resume get(String uuid) throws NotExistStorageException;

    void delete(String uuid) throws NotExistStorageException;

    Object[] getAll();

    int size();
}
