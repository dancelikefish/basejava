package ru.webapp.storage;

import ru.webapp.storage.serialization.XmlStreamSerializer;

import java.io.IOException;

public class XmlStreamPathSerializerTest extends AbstractStorageTest {
    public XmlStreamPathSerializerTest() throws IOException {
        super(new PathStorage(STORAGE_DIR.toString(), new XmlStreamSerializer()));
    }
}