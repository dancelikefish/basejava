package ru.webapp.storage;

import ru.webapp.storage.serialization.DataStreamSerializer;

import java.io.IOException;

public class DataStreamPathSerializerTest extends AbstractStorageTest {
    public DataStreamPathSerializerTest() throws IOException {
        super(new PathStorage(STORAGE_DIR.toString(), new DataStreamSerializer()));
    }
}