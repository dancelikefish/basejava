package ru.webapp.storage;

import ru.webapp.model.Resume;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class SerailizationContext {
    private SerializationStrategy strategy;

    public void setStrategy(SerializationStrategy strategy) {
        this.strategy = strategy;
    }

    public void deserialize(InputStream inputStream) throws IOException {
        strategy.deserialize(inputStream);
    }

    public void serialize(Resume resume, OutputStream outputStream) throws IOException {
        strategy.serialize(resume, outputStream);
    }
}
