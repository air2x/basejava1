package com.urise.webapp.storage;

import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.util.List;

public interface Storage {

    void clear();

    void update(Resume r) throws NotExistStorageException;

    void save(Resume r) throws StorageException;

    Resume get(String uuid) throws NotExistStorageException;

    void delete(String uuid) throws NotExistStorageException;

    List<Resume> getAllSorted();

    int size();
}
