package ru.webapp.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MainFile {
    public static void main(String[] args) {
        String filePath = "C:\\Users\\admin\\basejava";
        File file = new File(filePath);
        if (file.isDirectory()) {
            try {
                Files.walk(Paths.get(String.valueOf(file)))
                        .filter(Files::isRegularFile)
                        .forEach(System.out::println);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
