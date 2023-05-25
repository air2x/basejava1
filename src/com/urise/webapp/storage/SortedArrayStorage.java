package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    public void save(Resume r) {
        int indexFound = getIndex(r.getUuid());
        if (size >= STORAGE_LIMIT) {
            System.out.println("The resume database is full");
        } else if (indexFound <= INDEX_MISSING_RESUME) {
            indexFound = -(indexFound + 1);
            System.arraycopy(storage, indexFound, storage, indexFound + 1, size - indexFound);
            storage[indexFound] = r;
            size++;
        } else {
            System.out.println("There is already such a resume");
        }
    }

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}
