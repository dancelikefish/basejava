package ru.webapp.util;

import ru.webapp.model.Resume;
import ru.webapp.storage.ObjectStreamPathStorage;
import ru.webapp.storage.ObjectStreamStorage;
import ru.webapp.storage.SerailizationContext;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class SerializationTest {
    public static void main(String[] args) {
        SerailizationContext sct = new SerailizationContext();
        sct.setStrategy(new ObjectStreamStorage(new File("test")));
        try {
            sct.serialize(new Resume("test", "test"),
                    new ObjectOutputStream(new FileOutputStream("D:\\test")));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
