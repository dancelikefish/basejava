package ru.webapp.util;

import java.io.File;

public class MainFile {

    public void walk(String path) {
        File rootDirectory = new File(path);
        File[] listFiles = rootDirectory.listFiles();

        if (listFiles != null) {
            for (File file : listFiles) {
                if (file.isDirectory()) {
                    walk(file.getAbsolutePath());
                } else {
                    System.out.println(file.getName());
                }
            }
        }
    }

    public static void main(String[] args) {
        MainFile mainFile = new MainFile();
        mainFile.walk("C:\\Users\\admin\\IdeaProjects\\basejava");
    }
}
