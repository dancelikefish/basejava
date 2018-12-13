package ru.webapp.storage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.webapp.model.Resume;

import java.io.*;
import java.util.Objects;

public class ObjectStreamPathSerializerTest extends AbstractStorageTest {
    private ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("D:\\dir\\test"));
    private ObjectInputStream ois = new ObjectInputStream(new FileInputStream("D:\\dir\\test"));
    private SerializationStrategy ss = new ObjectStreamSerializer();

    public ObjectStreamPathSerializerTest() throws IOException {
        super(new PathStorage(STORAGE_DIR.toString(), new ObjectStreamSerializer()));
    }

    @Before
    public void serialize() {
        try {
            ss.serialize(R5, oos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void deserialize() {
        Resume R6 = null;
        try {
            R6 = ss.deserialize(ois);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(R5.toString(), Objects.requireNonNull(R6).toString());
    }


}