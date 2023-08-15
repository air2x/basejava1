package com.urise.webapp.storage;

import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.util.List;

public interface Storage {

    void clear() throws StorageException;

    void update(Resume r) throws StorageException;

    void save(Resume r) throws StorageException;

    Resume get(String uuid) throws StorageException;

    void delete(String uuid) throws StorageException;

    List<Resume> getAllSorted() throws StorageException;

    int size() throws StorageException;
}
