package ru.job4j.io;

import org.junit.Test;

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
}