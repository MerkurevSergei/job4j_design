package ru.job4j.io;

import org.testng.annotations.Test;

import java.nio.charset.StandardCharsets;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ParserTest {

    @Test
    public void testGetContentASCII() {
        final Parser p = new Parser();
        assertThat(p.getContentASCII("HelloА, world!Б"), is("Hello, world!"));
    }

    @Test
    public void testGetContent128() {
        final Parser p = new Parser();
        assertThat(p.getContent128("Г".getBytes(StandardCharsets.UTF_8)), is("\uFFD0ﾓ"));
    }
}