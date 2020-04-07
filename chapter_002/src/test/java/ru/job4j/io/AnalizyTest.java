package ru.job4j.io;

import org.junit.Test;

import java.io.*;
import java.util.StringJoiner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class AnalizyTest {

    @Test
    public void unavailable() throws IOException {
        String expected = "10:57:01;10:59:01;" + System.lineSeparator() + "11:01:02;11:02:02;";
        Analizy analizy = new Analizy();
        analizy.unavailable("src/test/resources/server.log", "src/test/resources/target.log");
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (final BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/target.log"))) {
            reader.lines().forEach(out::add);
        }
        assertThat(expected, is(out.toString()));
    }
}