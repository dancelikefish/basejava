package ru.webapp.storage;

import ru.webapp.exception.StorageException;
import ru.webapp.model.Resume;
import ru.webapp.storage.serialization.SerializationStrategy;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class PathStorage extends AbstractStorage<Path> {
    private Path directory;
    private SerializationStrategy serializationStrategy;

    protected PathStorage(String dir, SerializationStrategy serializationStrategy) {
        Objects.requireNonNull(dir, "directory mustn't be null");
        directory = Paths.get(dir);
        this.serializationStrategy = serializationStrategy;
        if (!Files.isDirectory(directory) || !Files.isWritable(directory)) {
            throw new IllegalArgumentException(dir + " is not readable/writable");
        }
    }

    @Override
    protected void saveInStorage(Resume resume, Path path) {
        try {
            Files.createFile(path);
        } catch (IOException e) {
            throw new StorageException("Couldn't create file " + path.toAbsolutePath().toString(), path.toString(), e);
        }
        updateInStorage(resume, path);
    }


    @Override
    protected void updateInStorage(Resume resume, Path path) {
        try {
            serializationStrategy.serialize(resume, new BufferedOutputStream(new ObjectOutputStream(new FileOutputStream(path.toFile()))));
        } catch (IOException e) {
            throw new StorageException("Path write error", resume.getUuid(), e);
        }
    }

    @Override
    protected Resume getFromStorage(Path path) {
        try {
            return serializationStrategy.deserialize(new BufferedInputStream(new ObjectInputStream(new FileInputStream(path.toFile()))));
        } catch (IOException e) {
            throw new StorageException("Path read error", path.getFileName().toString(), e);
        }
    }

    @Override
    protected void deleteFromStorage(Path path) {
        try {
            Files.delete(path);
        } catch (IOException e) {
            throw new StorageException("Delete path error", null);
        }
    }

    @Override
    protected boolean isValid(Path path) {
        return !Files.exists(path);
    }

    @Override
    protected Path getSearchKey(String uuid) {
        return Paths.get(directory.toString() + "\\" + uuid);
    }

    @Override
    protected List<Resume> getCopyList() {
        List<Resume> list = new ArrayList<>();
        try (Stream<Path> paths = Files.walk(Paths.get(directory.toString()))) {
            paths.filter(Files::isRegularFile).forEach(path -> list.add(getFromStorage(path)));
        } catch (IOException e) {
            throw new StorageException("CopyList error ", null);
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
            return (int) Files.list(Paths.get(directory.toString())).count();
        } catch (IOException e) {
            throw new StorageException("Path size error ", null);
        }
    }
}
