package ru.job4j.io.searcher;

import java.nio.file.*;
import java.util.Arrays;
import java.util.List;

/**
 * Contains and work with command line arguments from searcher
 */
public final class ArgsStore {
    /**
     * root search directory
     */
    private final Path directory;

    /**
     * pattern from search files
     */
    private final String pattern;

    /**
     * searching method
     */
    private final Method method;

    /**
     * output file
     */
    private final Path output;

    /**
     * @param args - init command line arguments
     */
    public ArgsStore(String[] args) {
        List<String> listArgs = Arrays.asList(args);
        directory = parseDirectory(listArgs);
        pattern = parsePattern(listArgs);
        method = parseMethod(listArgs);
        output = parseOutput(listArgs);
        checkDirectory();
        checkPattern();
        checkMethod();
    }

    /**
     * Directory getter.
     *
     * @return root search directory
     */
    public final Path getRootDirectory() {
        checkDirectory();
        return directory;
    }

    /**
     * Search pattern getter.
     *
     * @return pattern from search files
     */
    public final String getPattern() {
        checkPattern();
        return pattern;
    }

    /**
     * @return search method
     */
    public final Method getMethod() {
        checkMethod();
        return method;
    }

    /**
     * @return output file
     */
    public final Path getOutput() {
        return output;
    }

    /**
     * Parse root searching directory from command line arguments.
     *
     * @param args - command line arguments
     * @return - root searching directory
     */
    private Path parseDirectory(List<String> args) {
        Path res = null;
        int i = args.indexOf("-d");
        if (i != -1 && args.size() > i + 1) {
            Path path = Paths.get(args.get(i + 1));
            res = Files.exists(path) && Files.isDirectory(path) ? path : null;
        }
        return res;
    }

    /**
     * Parse search pattern from command line arguments.
     *
     * @param args - command line arguments
     * @return - pattern from search files
     */
    private String parsePattern(List<String> args) {
        String res = "";
        int i = args.indexOf("-n");
        if (i != -1 && args.size() > i + 1) {
            res = args.get(i + 1);
        }
        return res;
    }

    /**
     * Parse search method from command line arguments.
     *
     * @param args - command line arguments
     * @return search method
     */
    private Method parseMethod(List<String> args) {
        Method res = null;
        if (args.contains("-m")) {
            res = Method.MASK;
        } else if (args.contains("-f")) {
            res = Method.NAME;
        } else if (args.contains("-r")) {
            res = Method.REGEXP;
        }
        return res;
    }

    /**
     * Parse output file from command line arguments.
     *
     * @param args - command line arguments
     * @return output file
     */
    private Path parseOutput(List<String> args) {
        Path res = null;
        int i = args.indexOf("-o");
        if (i != -1 && args.size() > i + 1) {
            Path path = Paths.get(args.get(i + 1));
            res = Files.isWritable(path.toAbsolutePath().getParent()) && !Files.isDirectory(path) ? path : null;
        }
        return res;
    }

    /**
     * Check directory.
     */
    private void checkDirectory() {
        if (directory == null) {
            throw new IllegalStateException("Invalid directory");
        }
    }

    /**
     * Check pattern.
     */
    private void checkPattern() {
        if ("".equals(pattern)) {
            throw new IllegalStateException("Search pattern invalid");
        }
    }

    /**
     * Check method.
     */
    private void checkMethod() {
        if (method == null) {
            throw new IllegalStateException("Search method invalid");
        }
    }
}