package ru.job4j.io;

import java.io.File;
import java.nio.file.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Contains and work from command line arguments from zipped
 */
public class ArgZip {

    /**
     * command line arguments storage
     */
    final Map<String, String> args;

    /**
     * @param args - init command line arguments
     */
    public ArgZip(String[] args) {
        this.args = new HashMap<>();
        this.args.put("d", parseDirectory(args));
        this.args.put("e", parseExclude(args));
        this.args.put("o", parseOutput(args));
    }

    /**
     * @return the archived directory
     */
    public String directory() {
        return args.get("d");
    }

    /**
     * @return pattern from excluded files
     */
    public String exclude() {
        return args.get("e");
    }

    /**
     * @return result file name
     */
    public String output() {
        return args.get("o");
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
     * @return parse the archived directory
     */
    private String parseDirectory(String[] args) {
        final int i = Arrays.binarySearch(args, "-d");
        String res = extractValue(i + 1, args);
        if (!res.isEmpty()) {
            Path path = Paths.get(res);
            res = Files.exists(path) && path.toFile().isDirectory() ? path.toAbsolutePath().toString() : "";
        }
        return res;
    }

    /**
     * @return parse pattern from excluded files
     */
    private String parseExclude(String[] args) {
        final int i = Arrays.binarySearch(args, "-e");
        String res = extractValue(i + 1, args);
        if (!res.matches("^\\*\\.\\w+")) {
            res = "";
        }
        return res;
    }

    /**
     * @return parse result file name
     */
    private String parseOutput(String[] args) {
        final int i = Arrays.binarySearch(args, "-o");
        String res = extractValue(i + 1, args);
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
    private String extractValue(int i, String[] args) {
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