package ru.job4j.io;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;

import static java.nio.file.FileVisitResult.CONTINUE;

/**
 * Collect string path after filter ext
 */
public class ListFileVisitor implements FileVisitor<Path> {
    /**
     * list of string path
     */
    private List<String> list;

    /**
     * ext filter
     */
    private String pattern;

    /**
     * @param list - init list
     */
    public ListFileVisitor(List<String> list, String ext) {
        this.list = list;
        this.pattern = String.format("glob:**.%s", ext);
    }

    /**
     * @param dir   - dit
     * @param attrs - attrs
     * @return result
     * @throws IOException - exception
     */
    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        return CONTINUE;
    }

    /**
     * @param file  - file
     * @param attrs - attrs
     * @return - result
     * @throws IOException exception
     */
    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        final PathMatcher pathMatcher = FileSystems.getDefault().getPathMatcher(this.pattern);
        if (pathMatcher.matches(file)) {
            list.add(file.toAbsolutePath().normalize().toString());
        }
        return CONTINUE;
    }


    /**
     * @param file - file
     * @param exc  - exc
     * @return - result
     * @throws IOException exception
     */
    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
        return CONTINUE;
    }

    /**
     * @param dir - dit
     * @param exc - exc
     * @return - result
     * @throws IOException exception
     */
    @Override
    public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
        return CONTINUE;
    }

    /**
     * @return the expected files
     */
    public List<String> getList() {
        return list;
    }
}