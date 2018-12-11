package ru.webapp.storage;

import ru.webapp.exception.StorageException;
import ru.webapp.model.Resume;

import java.io.*;

public class ObjectStreamPathStorage extends AbstractPathStorage implements SerializationStrategy {
    public ObjectStreamPathStorage(String dir) {
        super(dir);
    }

    @Override
    public void serialize(Resume resume, OutputStream outputStream) throws IOException {
        try(ObjectOutputStream oos = new ObjectOutputStream(outputStream)) {
            oos.writeObject(resume);
        }
    }

    @Override
    public Resume deserialize(InputStream inputStream) throws IOException {
        try (ObjectInputStream ois = new ObjectInputStream(inputStream)) {
            return (Resume) ois.readObject();
        } catch (ClassNotFoundException e) {
            throw new StorageException("Error read resume", null, e);
        }
    }

    @Override
    protected void doWrite(Resume resume, OutputStream outputStream) throws IOException {
        serialize(resume, outputStream);
    }

    @Override
    protected Resume doRead(InputStream inputStream) throws IOException {
       return deserialize(inputStream);
    }
}
