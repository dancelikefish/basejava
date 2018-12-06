package ru.webapp.storage;

import ru.webapp.exception.StorageException;
import ru.webapp.model.Resume;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

public abstract class AbstractPathStorage extends AbstractStorage<Path> {
    private Path directory;

    protected abstract void doWrite(Resume resume, OutputStream outputStream) throws IOException;

    protected abstract Resume doRead(InputStream inputStream) throws IOException;

    protected AbstractPathStorage(String dir) {
        directory = Paths.get(dir);
        Objects.requireNonNull(directory, "directory mustn't be null");
        if (!Files.isDirectory(directory) || !Files.isWritable(directory)) {
            throw new IllegalArgumentException(dir + " is not readable/writable");
        }
    }

    @Override
    protected void saveInStorage(Resume resume, Path Path) {
        try {
            Path.createNewPath();
        } catch (IOException e) {
            throw new StorageException("Couldn't create Path " + Path.getAbsolutePath(), Path.getName(), e);
        }
        updateInStorage(resume, Path);
    }


    @Override
    protected void updateInStorage(Resume resume, Path Path) {
        try {
            doWrite(resume, new BufferedOutputStream(new PathOutputStream(Path)));
        } catch (IOException e) {
            throw new StorageException("Path write error", resume.getUuid(), e);
        }
    }

    @Override
    protected Resume getFromStorage(Path Path) {
        try {
            return doRead(new BufferedInputStream(new PathInputStream(Path)));
        } catch (IOException e) {
            throw new StorageException("Path read error", Path.getName(), e);
        }
    }

    @Override
    protected void deleteFromStorage(Path Path) {
        if (!Path.delete()) {
            throw new StorageException("Path delete error", Path.getName());
        }
    }

    @Override
    protected boolean isValid(Path path) {
        return !Files.exists(path);
    }

    @Override
    protected Path getSearchKey(String uuid) {
        return Paths.get(directory);
    }

    @Override
    protected List<Resume> getCopyList() {
        List<Resume> list = new ArrayList<>();
        for (Path Path : Objects.requireNonNull(Files.list(directory))) {
            list.add(getFromStorage(Path));
        }
        return list;
    }

    @Override
    public void clear() {
        try {
            Files.list(directory).forEach(this::deleteFromStorage);
        } catch (IOException e) {
            throw new StorageException("Path delete error ", null);
        }
    }

    @Override
    public int size() {
        try {
            return Objects.requireNonNull(Files.list(directory)).length;
        } catch (IOException e) {
            throw new StorageException("Path size error ", null);
        }
    }
}
