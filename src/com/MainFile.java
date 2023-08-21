package com;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainFile {
    public static void main(String[] args) {
//        String filePath = ".\\gitignore";
//
//        File file = new File(filePath);
//        try {
//            System.out.println(file.getCanonicalPath());
//        } catch (IOException e) {
//            throw new RuntimeException("Error", e);
//        }
//
//        File dir = new File("./src/com/urise/webapp");
//        System.out.println(dir.isDirectory());
//        String[] list = dir.list();
//        if (list != null) {
//            for (String name : list) {
//                System.out.println(name);
//            }
//        }
//
//        try (FileInputStream fis = new FileInputStream(filePath)) {
//            System.out.println(fis.read());
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

        List<File> listFile = new ArrayList<>();
        searchFiles(new File("C:/java/BaseJava/basejava/src/"), listFile, 0);
//        for (File file1 : listFile) {
//            System.out.println(file1.getName());
//        }
    }

    private static void searchFiles(File file, List<File> listFile, int level) {
        String str = repeat(level, "    ");
        if (!file.isFile()) {
            File[] directoryFiles = file.listFiles();
            if (directoryFiles != null) {
                for (File file1 : directoryFiles) {
                    if (!file1.isFile()) {
                        System.out.println(str + "Directory - " + file1.getName());
                        searchFiles(file1, listFile, level++);
                    } else {
                        System.out.println(str + "File - " + file1.getName());
                        listFile.add(file1);
                    }
                }
            }
        }
    }

    private static String repeat(int n, String value) {
        return new String(new char[n]).replace("\0", value);
    }
}
