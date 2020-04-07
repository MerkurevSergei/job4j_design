package ru.job4j.io;

import java.io.File;
import java.util.Objects;

/**
 * Work from directories.
 */
public class Dir {
    /**
     * root directory
     */
    File file;

    public Dir(String path) {
        file = new File(path);
        if (!file.exists()) {
            throw new IllegalStateException(String.format("Not exist %s", file.getAbsoluteFile()));
        }
        if (!file.isDirectory()) {
            throw new IllegalStateException(String.format("Not directory %s", file.getAbsoluteFile()));
        }
    }

    /**
     * @param folder - input File
     * @return long - File size
     */
    private long getFolderSize(File folder) {
        long length = 0;
        File[] files = folder.listFiles();
        int count = files != null ? files.length : 0;
        for (int i = 0; i < count; i++) {
            if (files[i].isFile()) {
                length += files[i].length();
            } else {
                length += getFolderSize(files[i]);
            }
        }
        return length;
    }

    /**
     * print inner files and directories name and size
     */
    public void print() {
        System.out.println(String.format("size : %s", file.getTotalSpace()));
        for (File subfile : Objects.requireNonNull(file.listFiles())) {
            if (subfile.isFile()) {
                System.out.println(String.format("%s %s", subfile.getName(), subfile.length()));
            } else {
                System.out.println(String.format("%s %s", subfile.getName(), getFolderSize(subfile)));
            }

        }
    }

    /**
     * @param args - console args
     */
    public static void main(String[] args) {
        new Dir("C:\\projects\\job4j_design").print();
    }
}