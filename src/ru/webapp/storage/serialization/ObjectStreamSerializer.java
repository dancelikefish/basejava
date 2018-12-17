package ru.webapp.storage.serialization;

import ru.webapp.exception.StorageException;
import ru.webapp.model.Resume;

import java.io.*;

public class ObjectStreamSerializer implements SerializationStrategy {

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
}
