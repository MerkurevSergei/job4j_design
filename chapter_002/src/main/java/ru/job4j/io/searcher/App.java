package ru.job4j.io.searcher;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public final class App {
    /**
     * @param args - command line arguments
     * @throws IOException exception
     */
    public static void main(String[] args) throws IOException {
        ArgsStore as = new ArgsStore(args);
        Searcher searcher = new Searcher(as.getRootDirectory(), as.getMethod());
        List<Path> files = searcher.execute();

        try (PrintWriter out = (as.getOutput() == null ? new PrintWriter(System.out)
                : new PrintWriter(Files.newOutputStream(as.getOutput())))) {
            for (Path file : files) {
                out.println(file);
            }
        }
    }
}
