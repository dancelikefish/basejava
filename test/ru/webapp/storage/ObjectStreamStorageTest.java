package ru.webapp.storage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.webapp.model.Resume;

import java.io.*;
import java.util.Objects;

public class ObjectStreamStorageTest extends AbstractStorageTest {
    private ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("D:\\dir\\test"));
    private ObjectInputStream ois = new ObjectInputStream(new FileInputStream("D:\\dir\\test"));
    private ObjectStreamStorage oss = new ObjectStreamStorage(STORAGE_DIR);

    public ObjectStreamStorageTest() throws IOException {
        super(new ObjectStreamStorage(STORAGE_DIR));
    }

    @Before
    public void doWrite() {
        try {
            oss.doWrite(R5, oos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void doRead() {
        Resume R6 = null;
        try {
            R6 = oss.doRead(ois);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(R5.toString(), Objects.requireNonNull(R6).toString());
    }

}