package com.urise.webapp.storage;

public class ArrayStorageTest extends AbstractArrayStorageTest {

    public ArrayStorageTest(Storage storage) {
        super(storage);
    }

    Storage arrayStorage = new ArrayStorage();
    ArrayStorageTest arrayStorageTest = new ArrayStorageTest(arrayStorage);
}