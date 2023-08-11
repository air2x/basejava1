package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.urise.webapp.storage.AbstractArrayStorage.STORAGE_LIMIT;

public abstract class AbstractArrayStorageTest extends AbstractStorageTest {


    public AbstractArrayStorageTest(Storage storage) {
        super(storage);
    }

    @Test
    public void arrayOverflowTest() throws StorageException {
        storage.clear();
        try {
            for (int i = 0; i < STORAGE_LIMIT; i++) {
                storage.save(new Resume(UUID + i, FULL_NAME + i));
            }
        } catch (StorageException e) {
            System.out.println(STORAGE_OVERFLOW);
        }
        StorageException thrown = Assertions.assertThrows(StorageException.class, () ->
                storage.save(new Resume(UUID_10000, FULL_NAME_10000)));
        Assertions.assertEquals(STORAGE_OVERFLOW, thrown.getMessage());
    }
}