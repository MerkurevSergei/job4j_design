package ru.job4j.io;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;
import java.util.StringJoiner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class AnalizyTest {
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void unavailable() throws IOException {
        String expected = "10:57:01;10:59:01;" + System.lineSeparator() + "11:01:02;11:02:02;";

        final String target = folder.newFile("target.txt").getAbsolutePath();
        Analizy analizy = new Analizy();
        analizy.unavailable("src/test/resources/server.txt", target);
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (final BufferedReader reader = new BufferedReader(new FileReader(target))) {
            reader.lines().forEach(out::add);
        }
        assertThat(expected, is(out.toString()));
    }
}