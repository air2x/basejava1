package com.urise.webapp.storage;

public class ArrayStorageTest extends AbstractArrayStorageTest {

    public ArrayStorageTest(Storage storage) {
        super(storage);
    }

    ArrayStorage arrayStorage = new ArrayStorage();
    ArrayStorageTest arrayStorageTest = new ArrayStorageTest(arrayStorage);
}