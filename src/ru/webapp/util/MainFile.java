package ru.webapp.util;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class MainFile {

    static void walk(int indent, File file) throws IOException {
        for (int i = 0; i < indent; i++) {
            System.out.print(" ");
        } System.out.println(file.getName());

        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File file1 : Objects.requireNonNull(files))
                walk(indent + 3, file1);
        }
    }

    public static void main(String[] a) throws IOException {
        walk(0, new File("C:\\Users\\admin\\IdeaProjects\\basejava"));
    }
}
