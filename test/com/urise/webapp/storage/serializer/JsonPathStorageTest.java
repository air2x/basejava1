package com.urise.webapp.storage.serializer;

import com.urise.webapp.storage.AbstractStorageTest;
import com.urise.webapp.storage.PathStorage;

class JsonPathStorageTest extends AbstractStorageTest {

    public JsonPathStorageTest() {
        super(new PathStorage(STORAGE_DIR_STRING, new JsonStreamSerializer()));
    }
}