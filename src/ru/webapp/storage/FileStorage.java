package ru.webapp.storage;

import ru.webapp.exception.StorageException;
import ru.webapp.model.Resume;
import ru.webapp.storage.serialization.SerializationStrategy;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FileStorage extends AbstractStorage<File> {
    private File directory;
    private SerializationStrategy serializationStrategy;

    protected FileStorage(File directory, SerializationStrategy serializationStrategy) {
        Objects.requireNonNull(directory, "directory mustn't be null");
        this.serializationStrategy = serializationStrategy;
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not directory");
        }
        if (!directory.canRead() || !directory.canWrite()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not readable/writable");
        }
        this.directory = directory;
    }

    @Override
    protected void saveInStorage(Resume resume, File file) {
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new StorageException("Couldn't create file " + file.getAbsolutePath(), file.getName(), e);
        }
        updateInStorage(resume, file);
    }


    @Override
    protected void updateInStorage(Resume resume, File file) {
        try {
            serializationStrategy.serialize(resume, new BufferedOutputStream(new FileOutputStream(file)));
        } catch (IOException e) {
            throw new StorageException("File write error", resume.getUuid(), e);
        }
    }

    @Override
    protected Resume getFromStorage(File file) {
        try {
            return serializationStrategy.deserialize(new BufferedInputStream(new FileInputStream(file)));
        } catch (IOException e) {
            throw new StorageException("File read error", file.getName(), e);
        }
    }

    @Override
    protected void deleteFromStorage(File file) {
        if (!file.delete()) {
            throw new StorageException("File delete error", file.getName());
        }
    }

    @Override
    protected boolean isValid(File file) {
        return !file.exists();
    }

    @Override
    protected File getSearchKey(String uuid) {
        return new File(directory, uuid);
    }

    @Override
    protected List<Resume> getCopyList() {
        List<Resume> list = new ArrayList<>();
        for (File file : Objects.requireNonNull(directory.listFiles())) {
            list.add(getFromStorage(file));
        }
        return list;
    }

    @Override
    public void clear() {
        for (File file : Objects.requireNonNull(directory.listFiles()))
            deleteFromStorage(file);
    }

    @Override
    public int size() {
        return Objects.requireNonNull(directory.listFiles()).length;
    }
}
