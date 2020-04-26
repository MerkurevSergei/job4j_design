package ru.job4j.io.searcher;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class ArgsStoreTest {
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void whenArgsSuccess() {
        final String[] args = new String[]{
                "-d", folder.getRoot().getAbsolutePath(),
                "-n", "*.txt",
                "-m",
                "-o", "log.txt"
        };
        ArgsStore argsStore = new ArgsStore(args);
        assertThat(argsStore.getRootDirectory().toString(), is(folder.getRoot().getAbsolutePath()));
        assertThat(argsStore.getPattern(), is("*.txt"));
        assertThat(argsStore.getMethod(), is(Method.MASK));
        assertThat(argsStore.getOutput().toString(), is("log.txt"));
    }

    @Test(expected = IllegalStateException.class)
    public void whenInvalidDirectory() {
        final String[] args = new String[]{
                "-n", "*.txt",
                "-m",
                "-o", "log.txt"
        };
        ArgsStore argsStore = new ArgsStore(args);
        fail();
    }

    @Test(expected = IllegalStateException.class)
    public void whenInvalidPattern() {
        final String[] args = new String[]{
                "-d", folder.getRoot().getAbsolutePath(),
                "-m",
                "-o", "log.txt"
        };
        ArgsStore argsStore = new ArgsStore(args);
        fail();
    }

    @Test(expected = IllegalStateException.class)
    public void whenInvalidMethod() {
        final String[] args = new String[]{
                "-d", folder.getRoot().getAbsolutePath(),
                "-n", "*.txt",
                "-g",
                "-o", "log.txt"
        };
        ArgsStore argsStore = new ArgsStore(args);
        fail();
    }
}