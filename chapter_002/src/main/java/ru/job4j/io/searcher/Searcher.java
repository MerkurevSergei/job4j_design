package ru.job4j.io.searcher;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Search files from mask, name or regular expression.
 * Have helper class PatternVisitor
 */
public final class Searcher {

    /**
     * The directory start searching
     */
    private final Path rootDirectory;

    /**
     * Pattern compare for file
     */
    private final String pattern;

    /**
     * Chosen visitor
     */
    private final PatternVisitor visitor;

    /**
     * @param rootDirectory - the directory start searching
     * @param pattern - pattern compare for file
     * @param method - method from choose visitor
     */
    public Searcher(Path rootDirectory, String pattern, Method method) {
        this.rootDirectory = rootDirectory;
        this.pattern = pattern;
        this.visitor = initVisitor(method);
    }

    /**
     * Execute search and return files when match with pattern
     * @return file list
     */
    public final List<Path> execute() {
        try {
            Files.walkFileTree(rootDirectory, visitor);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return visitor.result;
    }

    /**
     * @param method - search method
     * @return - visitor from method
     */
    private PatternVisitor initVisitor(Method method) {
        Map<Method, PatternVisitor> visitors = new HashMap<>();

        // Visitor find from mask
        visitors.put(Method.MASK, new PatternVisitor() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
                final PathMatcher pathMatcher = FileSystems.getDefault().getPathMatcher("glob:" + pattern);
                if (pathMatcher.matches(file.getFileName())) {
                    result.add(file);
                }
                return FileVisitResult.CONTINUE;
            }
        });

        // Visitor find from name
        visitors.put(Method.NAME, new PatternVisitor() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
                if (pattern.equals(file.getFileName().toString())) {
                    result.add(file);
                }
                return FileVisitResult.CONTINUE;
            }
        });

        // Visitor find from regular expression
        visitors.put(Method.REGEXP, new PatternVisitor() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
                final PathMatcher pathMatcher = FileSystems.getDefault().getPathMatcher("regex:" + pattern);
                if (pathMatcher.matches(file)) {
                    result.add(file);
                }
                return FileVisitResult.CONTINUE;
            }
        });

        return visitors.get(method);
    }

    /**
     * Pattern visitor extends SimpleFileVisitor, add result list
     * and override visitFileFailed
     */
    private static class PatternVisitor extends SimpleFileVisitor<Path> {
        protected List<Path> result = new ArrayList<>();

        @Override
        public FileVisitResult visitFileFailed(Path file, IOException exc) {
            return FileVisitResult.CONTINUE;
        }
    }
}
