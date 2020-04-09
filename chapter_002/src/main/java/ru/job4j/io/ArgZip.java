package ru.job4j.io;

import java.io.File;
import java.nio.file.*;
import java.util.Arrays;

/**
 * Contains and work from command line arguments from zipped
 */
public class ArgZip {

    /**
     * command line arguments
     */
    private final String[] args;

    /**
     * @param args - init command line arguments
     */
    public ArgZip(String[] args) {
        this.args = args;
    }

    /**
     * @return true if arguments valid
     */
    public boolean valid() {
        boolean res = true;
        if (directory().isEmpty() || exclude().isEmpty() || output().isEmpty()) {
            res = false;
        }
        return res;
    }

    /**
     * @return the archived directory
     */
    public String directory() {
        final int i = Arrays.binarySearch(args, "-d");
        String res = extractValue(i + 1);
        if (!res.isEmpty()) {
            Path path = Paths.get(res);
            res = Files.exists(path) && path.toFile().isDirectory() ? path.toAbsolutePath().toString() : "";
        }
        return res;
    }

    /**
     * @return pattern from excluded files
     */
    public String exclude() {
        final int i = Arrays.binarySearch(args, "-e");
        String res = extractValue(i + 1);
        if (!res.matches("^\\*\\.\\w+")) {
            res = "";
        }
        return res;
    }

    /**
     * @return result file name
     */
    public String output() {
        final int i = Arrays.binarySearch(args, "-o");
        String res = extractValue(i + 1);
        if (!res.isEmpty() && !Paths.get(res).getParent().toFile().exists()) {
            res = "";
        }
        final PathMatcher pathMatcher = FileSystems.getDefault()
                .getPathMatcher("regex:(.+)?[><\\\\|\\\\?*/:\\\\\\\\\\\"](.+)?");
        if (pathMatcher.matches(Paths.get(res).getFileName())) {
            res = "";
        }
        return res;
    }

    /**
     * @param i - key from array for extract directories
     * @return - parameter value
     */
    private String extractValue(int i) {
        String res = "";
        try {
            res = args[i];
        } catch (ArrayIndexOutOfBoundsException ignored) {
        }
        switch (res) {
            case "-d":
            case "-e":
            case "-o":
                res = "";
                break;
            default:
        }
        return res;
    }
}