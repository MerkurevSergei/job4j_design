package ru.job4j.io.searcher;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.nio.file.Path;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class SearcherTest {
    File file0;
    File file1;
    File file2;

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Before
    public void setUp() throws Exception {
        file0 = folder.newFile("a.txt");
        file1 = folder.newFile("b.log");
        file2 = folder.newFile("c.txt");
    }

    @Test
    public void whenSearchByMask() {
        final String[] args = new String[]{
                "-d", folder.getRoot().getAbsolutePath(),
                "-n", "*.txt",
                "-m",
                "-o", "log.txt"
        };
        ArgsStore argsStore = new ArgsStore(args);
        Searcher searcher = new Searcher(argsStore.getRootDirectory(), argsStore.getMethod());
        List<Path> files = searcher.execute();
        assertThat(files, is(List.of(file0.toPath(), file2.toPath())));
    }

    @Test
    public void whenSearchByName() {
        final String[] args = new String[]{
                "-d", folder.getRoot().getAbsolutePath(),
                "-n", "a.txt",
                "-f",
                "-o", "log.txt"
        };
        ArgsStore argsStore = new ArgsStore(args);
        Searcher searcher = new Searcher(argsStore.getRootDirectory(), argsStore.getMethod());
        List<Path> files = searcher.execute();
        assertThat(files, is(List.of(file0.toPath())));
    }

    @Test
    public void whenSearchByRegEx() {
        final String[] args = new String[]{
                "-d", folder.getRoot().getAbsolutePath(),
                "-n", "\\S+\\.txt",
                "-r",
                "-o", "log.txt"
        };
        ArgsStore argsStore = new ArgsStore(args);
        Searcher searcher = new Searcher(argsStore.getRootDirectory(), argsStore.getMethod());
        List<Path> files = searcher.execute();
        assertThat(files, is(List.of(file0.toPath(), file2.toPath())));
    }
}