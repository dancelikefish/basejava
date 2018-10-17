package ru.webapp;

import ru.webapp.model.Resume;

import java.util.ArrayList;
import java.util.Collection;
import java.util.*;
import java.util.Iterator;

public class MainCollections {
    private static final Resume r1 = new Resume("uuid1");
    private static final Resume r2 = new Resume("uuid2");
    private static final Resume r3 = new Resume("uuid3");

    public static void main(String[] args) {
        Collection<Resume> collection = new ArrayList<>();

        collection.add(r1);
        collection.add(r2);
        collection.add(r3);

        Iterator<Resume> iterator = collection.iterator();

        while (iterator.hasNext()) {
            Resume r = iterator.next();
            System.out.println(r);
            if (r.getUuid().equals(r1.getUuid())) {
                iterator.remove();
            }
        }
        System.out.println(collection.toString());


        HashMap<String, Resume> map = new HashMap<>();
        map.put("uuid1", r1);
        map.put("uuid2", r2);
        map.put("uuid3", r3);

        for (Map.Entry<String, Resume> entry : map.entrySet()) {
            System.out.println(entry.getValue());
        }
    }
}
