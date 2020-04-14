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
    public void whenSaveAndReadSuccess() {
        String expected = "0:0:0:0:0:0:0:1 - - [19/Feb/2020:15:21:18 +0300] \"GET /items/ajax.html HTTP/1.1\""
                + " 404 1113" + System.lineSeparator()
                + "0:0:0:0:0:0:0:1 - - [19/Feb/2020:15:21:23 +0300] \"GET /TrackStudio/ HTTP/1.1\""
                + " 404 1110" + System.lineSeparator()
                + "0:0:0:0:0:0:0:1 - - [19/Feb/2020:15:21:34 +0300] \"GET /TrackStudioNew/ HTTP/1.1\" 404 -";

        List<String> log = LogFilter.filter("src/test/resources/log.txt");
        LogFilter.save(log, "src/test/resources/404.txt");
        String out = LogFilter.read("src/test/resources/404.txt");
        assertThat(expected, is(out));
    }
}