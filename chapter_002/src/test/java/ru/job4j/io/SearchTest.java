package ru.job4j.io;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.StringJoiner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class SearchTest {
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void search() throws IOException {
        final File file = folder.newFile("two.java");
        final File file1 = folder.newFile("five.java");
        folder.newFile("one.txt");
        folder.newFile("three.pjava");
        folder.newFile("four.javac");

        String expected = String.format("[%s, %s]", file1.getAbsolutePath(), file.getAbsolutePath());

        final List<String> outList = Search.search(folder.getRoot().toPath(), "java");
        outList.sort(String::compareTo);
        assertThat(expected, is(outList.toString()));
    }

    @Test
    public void whenSearchArgsSuccess() throws IOException {
        final File file = folder.newFile("two.java");
        final File file1 = folder.newFile("five.java");
        folder.newFile("one.txt");
        folder.newFile("three.pjava");
        folder.newFile("four.javac");

        String expected = String.format("[%s, %s]", file1.getAbsolutePath(), file.getAbsolutePath());

        String[] args = new String[]{folder.getRoot().toPath().toString(), "java"};
        final List<String> outList = Search.search(args);
        outList.sort(String::compareTo);
        assertThat(expected, is(outList.toString()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenSearchArgsIllegalArgumentException() throws IOException {
        final File file = folder.newFile("two.java");
        folder.newFile("one.txt");

        String[] args = new String[]{file.toString(), "java"};
        final List<String> outList = Search.search(args);
        outList.sort(String::compareTo);
        assertThat("", is(outList.toString()));
    }
}