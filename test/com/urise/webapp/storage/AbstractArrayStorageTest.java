package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public abstract class AbstractArrayStorageTest {

    private final Storage storage;

    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @BeforeEach
    public void setUp() throws StorageException {
        storage.clear();
        storage.save(new Resume(UUID_1));
        storage.save(new Resume(UUID_2));
        storage.save(new Resume(UUID_3));
    }

    @Test
    public void size() {
        assertEquals(3, storage.size());
    }

    @Test
    public void clear() {
        storage.clear();
        assertEquals(0, storage.size());
    }

    @Test
    public void update() throws NotExistStorageException {
        storage.update(storage.get(UUID_1));
        assertEquals(storage.get(UUID_1), storage.get(UUID_1));
    }

    @Test
    public void save() throws NotExistStorageException {
        assertEquals(new Resume(UUID_1), storage.get("uuid1"));
    }

    @Test
    public void get() throws NotExistStorageException {
        assertEquals(new Resume(UUID_1), storage.get("uuid1"));
    }

    @Test
    public void delete() throws NotExistStorageException {
        storage.delete("uuid1");
        NotExistStorageException thrown = Assertions.assertThrows(NotExistStorageException.class, () ->
                storage.get("uuid1"));
        Assertions.assertEquals("Resume uuid1 not exist", thrown.getMessage());
    }

    @Test
    public void getAll() {
        Resume[] resumes = new Resume[3];
        resumes[0] = new Resume(UUID_1);
        resumes[1] = new Resume(UUID_2);
        resumes[2] = new Resume(UUID_3);
        assertArrayEquals(resumes, storage.getAll());
    }

    @Test
    public void updateNotExist() {
        NotExistStorageException thrown = Assertions.assertThrows(NotExistStorageException.class, () ->
                storage.update(new Resume("dummy")));
        Assertions.assertEquals("Resume dummy not exist", thrown.getMessage());
    }

    @Test
    public void saveAlreadyExist() {
        storage.clear();
        ExistStorageException thrown = Assertions.assertThrows(ExistStorageException.class, () ->
                storage.save(new Resume(UUID_1)));
        Assertions.assertEquals("Resume uuid1 already exist", thrown.getMessage());
    }

    @Test
    public void getNotExist() {
        NotExistStorageException thrown = Assertions.assertThrows(NotExistStorageException.class, () ->
                storage.get("dummy"));
        Assertions.assertEquals("Resume dummy not exist", thrown.getMessage());
    }

    @Test
    public void deleteNotExist() {
        NotExistStorageException thrown = Assertions.assertThrows(NotExistStorageException.class, () ->
                storage.delete("dummy"));
        Assertions.assertEquals("Resume dummy not exist", thrown.getMessage());
    }

    @Test
    public void arrayOverflowTest() throws StorageException {
        storage.clear();
        for (int i = 0; i < 10000; i++) {
            storage.save(new Resume("uuid" + i));
        }
        StorageException thrown = Assertions.assertThrows(StorageException.class, () ->
                storage.save(new Resume("uuid10000")));
        Assertions.assertEquals("Storage overflow", thrown.getMessage());
    }
}