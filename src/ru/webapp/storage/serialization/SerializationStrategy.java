package ru.webapp.storage.serialization;

import ru.webapp.model.Resume;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface SerializationStrategy {
    void serialize(Resume resume, OutputStream outputStream) throws IOException;
    Resume deserialize(InputStream inputStream) throws IOException;
}
