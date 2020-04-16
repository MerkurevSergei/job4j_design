package ru.job4j.io;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NotDirectoryException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Search example
 */
public final class Search {

    /**
     * @param args - command line arguments
     * @throws IOException exception
     */
    public static void main(String[] args) throws IOException {
        Path start = Paths.get("./chapter_002/src/main/java");
        List<String> files = search(start, "java");
        System.out.println(files);
        files = search(args);
        System.out.println(files);
    }

    /**
     * @param root - root directory
     * @param ext - extension
     * @return - result of search from extension
     * @throws IOException - exception
     */
    public static List<String> search(Path root, String ext) throws IOException {
        final ListFileVisitor listFileVisitor = new ListFileVisitor(new ArrayList<>(), ext);
        Files.walkFileTree(root, listFileVisitor);
        return listFileVisitor.getList();
    }

    /**
     * @param args - command line arguments
     * @return - result of search from extension
     * @throws IOException - exception
     */
    public static List<String> search(String[] args) throws IOException {
        checkArgs(args);
        final ListFileVisitor listFileVisitor = new ListFileVisitor(new ArrayList<>(), args[1]);
        Files.walkFileTree(Paths.get(args[0]), listFileVisitor);
        return listFileVisitor.getList();
    }

    /**
     * @param args command line arguments
     */
    public static void checkArgs(String[] args) throws NotDirectoryException, FileNotFoundException {

        if (args.length < 2) {
            throw new IllegalArgumentException("Command line arguments is not valid");
        }
        if (!Paths.get(args[0]).toFile().exists()) {
            throw new NotDirectoryException("File not found");
        }
        if (Paths.get(args[0]).toFile().isFile()) {
            throw new FileNotFoundException();
        }

    }
}