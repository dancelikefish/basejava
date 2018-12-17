package ru.webapp.util;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class MainFile {

    static void walk(File file) throws IOException {
        File[] files = file.listFiles();
        for (File file1 : Objects.requireNonNull(files))
            if (file1.isDirectory()) {
                System.out.println(file1.getName());
                walk(file1);
            } else
                System.out.println("    " + file1.getName());
    }

    public static void main(String[] a) throws IOException {
        walk(new File("C:\\Users\\admin\\IdeaProjects\\basejava"));
    }
}
