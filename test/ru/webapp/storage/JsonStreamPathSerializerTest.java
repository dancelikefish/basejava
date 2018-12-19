package ru.webapp.storage;

import ru.webapp.storage.serialization.JsonStreamSerializer;

import java.io.IOException;

public class JsonStreamPathSerializerTest extends AbstractStorageTest {
    public JsonStreamPathSerializerTest() throws IOException {
        super(new PathStorage(STORAGE_DIR.toString(), new JsonStreamSerializer()));
    }
}