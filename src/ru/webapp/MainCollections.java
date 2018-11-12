package ru.webapp;

import ru.webapp.model.Resume;

import java.util.*;

public class MainCollections {
    private static final Resume r1 = new Resume("uuid1");
    private static final Resume r2 = new Resume("uuid2");
    private static final Resume r3 = new Resume("uuid3");

    public static void main(String[] args) {
        List<Resume> collection = new ArrayList<>();



        collection.add(r1);
        collection.add(r2);
        collection.add(r3);
        collection.size();


        System.out.println(collection.contains(new Resume("uuid1")));
        System.out.println(collection.indexOf(r3));
        System.out.println(collection.remove(new Resume("uuid2")));
        System.out.println(collection.toString());
        Iterator<Resume> iterator = collection.iterator();

        while (iterator.hasNext()) {
            Resume r = iterator.next();
            //System.out.println(r);
            if (r.getUuid().equals(r1.getUuid())) {
                iterator.remove();
            }
        }


        Resume r  = new Resume("asd");

        HashMap<String, Resume> map = new LinkedHashMap<>();
        map.put("uuid3", r2);
        map.put("uuid2", r1);
        map.put("uuid4", r2);
        map.put("uuid10", r2);
        map.put("asv", r2);

    }

}

