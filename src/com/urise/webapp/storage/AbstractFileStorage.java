package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class AbstractFileStorage extends AbstractStorage<File> {
    private final File directory;

    protected AbstractFileStorage(File directory) {
        Objects.requireNonNull(directory, "directory must not be null");
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not directory");
        }
        if (!directory.canRead() || !directory.canWrite()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not readable/writable");
        }
        this.directory = directory;
    }

    @Override
    protected boolean isExist(File file) {
        return file.exists();
    }

    @Override
    protected File getSearchKey(String uuid) {
        return new File(directory, uuid);
    }

    @Override
    protected void doUpdate(File file, Resume r) throws StorageException {
        try {
            doWrite(r, new BufferedOutputStream(new FileOutputStream(file)));
        } catch (IOException e) {
            throw new StorageException("File write error", r.getUuid(), e);
        }
    }

    @Override
    protected void doSave(File file, Resume r) throws StorageException {
        try {
            file.createNewFile();
            doWrite(r, new BufferedOutputStream(Files.newOutputStream(file.toPath())));
        } catch (IOException e) {
            throw new StorageException("IO error", file.getName(), e);
        }
    }

    @Override
    protected Resume doGet(File file) throws StorageException {
        try {
            return doRead(new BufferedInputStream(new FileInputStream(file)));
        } catch (IOException e) {
            throw new StorageException("File read error", file.getName(), e);
        }
    }

    @Override
    protected void doDelete(File file) throws StorageException {
        if (!file.delete()) {
            throw new StorageException("do not deleted", file.getName());
        }
    }

    @Override
    protected List<Resume> doGetAllSorted() throws StorageException {
        File[] files = directory.listFiles();
        if (files == null) {
            throw new StorageException("directory null", null);
        }
        List<Resume> resumes = new ArrayList<>();
        for (File file : files) {
            resumes.add(doGet(file));
        }
        return resumes;
    }

    @Override
    public void clear() throws StorageException {
        if (!directory.delete()) {
            throw new StorageException("directory do not deleted", directory.getName());
        }
    }

    @Override
    public int size() throws StorageException {
        File[] files = directory.listFiles();
        if (files == null) {
            throw new StorageException("directory null", null);
        }
        return files.length;
    }

    protected abstract void doWrite(Resume r, OutputStream os) throws IOException;

    protected abstract Resume doRead(InputStream is) throws StorageException, IOException;
}
