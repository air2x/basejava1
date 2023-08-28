package com.urise.webapp.storage.serializer;

import com.urise.webapp.storage.AbstractStorageTest;
import com.urise.webapp.storage.PathStorage;

class XmlPathStorageTest extends AbstractStorageTest {
    public XmlPathStorageTest() {
        super(new PathStorage(STORAGE_DIR_STRING, new XmlStreamSerializer()));
    }
}