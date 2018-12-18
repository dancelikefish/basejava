package ru.webapp.storage;

import ru.webapp.storage.serialization.ObjectStreamSerializer;

import java.io.IOException;

public class ObjectStreamPathSerializerTest extends AbstractStorageTest {
    public ObjectStreamPathSerializerTest() throws IOException {
        super(new PathStorage(STORAGE_DIR.toString(), new ObjectStreamSerializer()));
    }
}