package ru.webapp.storage;

import ru.webapp.storage.serialization.ObjectStreamSerializer;

import java.io.File;
import java.io.IOException;

public class ObjectStreamFileSerializerTest extends AbstractStorageTest {
    public ObjectStreamFileSerializerTest() throws IOException {
        super(new FileStorage(new File(STORAGE_DIR.toString()), new ObjectStreamSerializer()));
    }
}