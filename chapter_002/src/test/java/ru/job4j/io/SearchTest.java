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
        folder.newFile("one.txt");
        final File file = folder.newFile("two.java");
        folder.newFile("three.pjava");
        folder.newFile("four.javac");
        final File file1 = folder.newFile("five.java");
        String expected = String.format("[%s, %s]", file1.getAbsolutePath(), file.getAbsolutePath());

        final List<String> outList = Search.search(folder.getRoot().toPath(), "java");
        outList.sort(String::compareTo);
        assertThat(expected, is(outList.toString()));
    }
}