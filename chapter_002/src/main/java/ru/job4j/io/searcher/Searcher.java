package ru.job4j.io.searcher;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

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
     * Chosen visitor
     */
    private final PatternVisitor visitor;

    /**
     * @param rootDirectory - the directory start searching
     * @param method - method from searching
     */
    public Searcher(Path rootDirectory, Predicate<Path> method) {
        this.rootDirectory = rootDirectory;
        this.visitor = new PatternVisitor(method);
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
     * Pattern visitor extends SimpleFileVisitor, add result list
     * and override visitFileFailed
     */
    private static class PatternVisitor extends SimpleFileVisitor<Path> {
        protected List<Path> result = new ArrayList<>();
        Predicate<Path> method;
        public PatternVisitor(Predicate<Path> method) {
            this.method = method;
        }

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
            if (method.test(file.getFileName())) {
                result.add(file);
            }
            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult visitFileFailed(Path file, IOException exc) {
            return FileVisitResult.CONTINUE;
        }
    }
}
