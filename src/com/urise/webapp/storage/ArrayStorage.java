package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int size;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void save(Resume r) {
        if (size == storage.length) {
            System.out.println("The resume database is full");
        } else {
            if (searchResume(r)) {
                System.out.println("There is already such a resume");
            } else {
                storage[size] = r;
                size++;
            }
        }
    }

    public Resume get(String uuid) {
        if (searchUuid(uuid)) {
            for (int i = 0; i < size; i++) {
                if (uuid.equals(storage[i].getUuid())) {
                    return storage[i];
                }
            }
        } else {
            printResumeNotFound(uuid);
        }
        return null;
    }

    private void printResumeNotFound(String uuid) {
        System.out.println(uuid + " not found");
    }

    public void update(Resume r) {
        Scanner scanner = new Scanner(System.in);
        if (!searchResume(r)) {
            System.out.println("Resume not found");
        } else {
            for (int i = 0; i < size; i++) {
                if (r == storage[i]) {
                    System.out.println("Enter a new uuid");
                    String uuid = scanner.nextLine();
                    storage[i].setUuid(uuid);
                }
            }
        }
    }

    private boolean searchResume(Resume r) {
        for (int i = 0; i < size; i++) {
            if (r == storage[i]) {
                return true;
            }
        }
        return false;
    }


    private boolean searchUuid(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return true;
            }
        }
        return false;
    }

    public void delete(String uuid) {
        if (searchUuid(uuid)) {
            for (int i = 0; i < size; i++) {
                if (storage[i].getUuid().equals(uuid)) {
                    System.arraycopy(storage, i + 1, storage, i, size - i - 1);
                    storage[size - 1] = null;
                    size--;
                    break;
                }
            }
        } else {
            printResumeNotFound(uuid);
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }
}
