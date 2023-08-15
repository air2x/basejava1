package com.urise.webapp.storage;

import com.ResumeTestData;
import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

public abstract class AbstractStorageTest {

    protected static final File STORAGE_DIR = new File("C:\\java\\BaseJava\\storage");

    protected final Storage storage;

    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_4 = "uuid4";
    private static final String FULL_NAME_1 = "full name1";
    private static final String FULL_NAME_2 = "full name2";
    private static final String FULL_NAME_3 = "full name3";
    private static final String FULL_NAME_4 = "full name4";
    protected static final String UUID = "uuid";
    protected static final String FULL_NAME = "full name";
    protected static final String UUID_10000 = "uuid10000";
    protected static final String FULL_NAME_10000 = "full name10000";
    private static final String UUID_NOT_EXIST = "dummy";
    private static final String FULL_NAME_NOT_EXIST = "dummy";
    protected static final String STORAGE_OVERFLOW = "Storage overflow";
    protected static ResumeTestData rst = new ResumeTestData();
    private static final Resume RESUME_1 = rst.createResume(UUID_1, FULL_NAME_1);
    private static final Resume RESUME_2 = rst.createResume(UUID_2, FULL_NAME_2);
    private static final Resume RESUME_3 = rst.createResume(UUID_3, FULL_NAME_3);
    private static final Resume RESUME_4 = rst.createResume(UUID_4, FULL_NAME_4);



    public AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @BeforeEach
    public void setUp() throws StorageException {
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
    }

    @Test
    public void size() throws StorageException {
        assertSize(3);
    }

    public void assertSize(int size) throws StorageException {
        assertEquals(size, storage.size());
    }

    @Test
    public void clear() throws StorageException {
        storage.clear();
        assertSize(0);
        Resume[] resumes = new Resume[0];
        assertArrayEquals(resumes, storage.getAllSorted().toArray());
    }

    @Test
    public void update() throws StorageException {
        Resume resumeCopy = new Resume(UUID_1, FULL_NAME_1);
        storage.update(RESUME_1);
        assertNotSame(resumeCopy, RESUME_1);
    }

    @Test
    public void save() throws StorageException {
        storage.save(RESUME_4);
        assertGet(RESUME_4);
        assertSize(4);
    }

    @Test
    public void get() throws StorageException {
        assertGet(RESUME_1);
        assertGet(RESUME_2);
        assertGet(RESUME_3);
    }

    public void assertGet(Resume resume) throws StorageException {
        assertEquals(resume, storage.get(resume.getUuid()));
    }

    @Test
    public void delete() throws StorageException {
        storage.delete(UUID_1);
        assertSize(2);
        NotExistStorageException thrown = Assertions.assertThrows(NotExistStorageException.class, () ->
                storage.get(UUID_1));
        Assertions.assertEquals(getStringAboutResumeNotExist(UUID_1), thrown.getMessage());
    }

    public String getStringAboutResumeNotExist(String uuid) {
        return "Resume " + uuid + " not exist";
    }

    @Test
    public void getAllSorted() throws StorageException {
        Resume[] resumes = new Resume[]{RESUME_1, RESUME_2, RESUME_3};
        assertArrayEquals(resumes, storage.getAllSorted().toArray());
    }

    @Test
    public void updateNotExist() {
        NotExistStorageException thrown = Assertions.assertThrows(NotExistStorageException.class, () ->
                storage.update(new Resume(UUID_NOT_EXIST, FULL_NAME_NOT_EXIST)));
        Assertions.assertEquals(getStringAboutResumeNotExist(UUID_NOT_EXIST), thrown.getMessage());
    }

    @Test
    public void saveAlreadyExist() {
        ExistStorageException thrown = Assertions.assertThrows(ExistStorageException.class, () ->
                storage.save(new Resume(UUID_1, FULL_NAME_1)));
        Assertions.assertEquals(getStringAboutResumeExist(UUID_1), thrown.getMessage());
    }

    public String getStringAboutResumeExist(String uuid) {
        return "Resume " + uuid + " already exist";
    }

    @Test
    public void getNotExist() {
        NotExistStorageException thrown = Assertions.assertThrows(NotExistStorageException.class, () ->
                storage.get(UUID_NOT_EXIST));
        Assertions.assertEquals(getStringAboutResumeNotExist(UUID_NOT_EXIST), thrown.getMessage());
    }

    @Test
    public void deleteNotExist() {
        NotExistStorageException thrown = Assertions.assertThrows(NotExistStorageException.class, () ->
                storage.delete(UUID_NOT_EXIST));
        Assertions.assertEquals(getStringAboutResumeNotExist(UUID_NOT_EXIST), thrown.getMessage());
    }
}