package ru.job4j.io;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.StringJoiner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class LogFilterTest {

    @Test
    public void filter() {
        String expected = "[0:0:0:0:0:0:0:1 - - [19/Feb/2020:15:21:18 +0300] \"GET /items/ajax.html HTTP/1.1\""
                + " 404 1113, 0:0:0:0:0:0:0:1 - - [19/Feb/2020:15:21:23 +0300] \"GET /TrackStudio/ HTTP/1.1\""
                + " 404 1110, 0:0:0:0:0:0:0:1 - - [19/Feb/2020:15:21:34 +0300] \"GET /TrackStudioNew/ HTTP/1.1\" 404 -]";
        String out = LogFilter.filter("src/test/resources/log.txt").toString();
        assertThat(expected, is(out));
    }

    @Test
    public void save() {
        String expected = "0:0:0:0:0:0:0:1 - - [19/Feb/2020:15:21:18 +0300] \"GET /items/ajax.html HTTP/1.1\""
                + " 404 1113\r\n0:0:0:0:0:0:0:1 - - [19/Feb/2020:15:21:23 +0300] \"GET /TrackStudio/ HTTP/1.1\""
                + " 404 1110\r\n0:0:0:0:0:0:0:1 - - [19/Feb/2020:15:21:34 +0300] \"GET /TrackStudioNew/ HTTP/1.1\" 404 -";

        List<String> log = LogFilter.filter("src/test/resources/log.txt");
        LogFilter.save(log, "src/test/resources/404.txt");
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (final BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/404.txt"))) {
            reader.lines().forEach(out::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertThat(expected, is(out.toString()));
    }
}