package ru.webapp.storage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.webapp.model.Resume;
import ru.webapp.storage.serialization.ObjectStreamSerializer;
import ru.webapp.storage.serialization.SerializationStrategy;

import java.io.*;
import java.util.Objects;

public class ObjectStreamPathSerializerTest extends AbstractStorageTest {
    public ObjectStreamPathSerializerTest() throws IOException {
        super(new PathStorage(STORAGE_DIR.toString(), new ObjectStreamSerializer()));
    }
}