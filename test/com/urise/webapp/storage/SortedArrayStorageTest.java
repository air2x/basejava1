package com.urise.webapp.storage;

public class SortedArrayStorageTest extends AbstractArrayStorageTest {

    public SortedArrayStorageTest(Storage storage) {
        super(storage);
    }

    Storage sortedArrayStorage = new SortedArrayStorage();
    SortedArrayStorageTest sortedArrayStorageTest = new SortedArrayStorageTest(sortedArrayStorage);
}