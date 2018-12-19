package ru.webapp.util;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class MainFile {

    public static void walk(File file, int depth) throws IOException {
        File[] files = file.listFiles();

        for (File file1 : Objects.requireNonNull(files))
            if (file1.isDirectory()) {
                System.out.println(repeat(" ", depth) + "Directory: " + file1.getName());
                walk(file1, depth+2);
            } else
                System.out.println(repeat(" ", depth) + "File: " + file1.getName());
    }

    public static String repeat(String str, int times) {
        return new String(new char[times]).replace("\t", str);
    }

    public static void main(String[] a) throws IOException {
        walk(new File("C:\\Users\\admin\\IdeaProjects\\basejava"), 0);
    }
}
