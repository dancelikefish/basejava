package ru.webapp;

import ru.webapp.model.Resume;

import java.util.ArrayList;
import java.util.Collection;

public class MainCollections {
    public static void main(String[] args) {
        Collection<Resume> collection = new ArrayList<>();

        Resume r1 = new Resume("uuid1");
        Resume r2 = new Resume("uuid2");
        Resume r3 = new Resume("uuid3");

    }
}
