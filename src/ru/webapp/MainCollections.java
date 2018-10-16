package ru.webapp;

import ru.webapp.model.Resume;

import java.util.ArrayList;
import java.util.Collection;

public class MainCollections {
    private static final Resume r1 = new Resume("uuid1");
    private static final Resume r2 = new Resume("uuid2");
    private static final Resume r3 = new Resume("uuid3");

    public static void main(String[] args) {
        Collection<Resume> collection = new ArrayList<>();

        collection.add(r1);
        collection.add(r2);
        collection.add(r3);
    }
}
