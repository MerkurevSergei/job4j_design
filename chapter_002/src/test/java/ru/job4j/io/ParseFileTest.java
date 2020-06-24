package ru.job4j.io;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ParseFileTest {

    private Path path;

    @BeforeSuite(alwaysRun = true)
    public void setUp() throws IOException {
        path = Files.createTempFile(null, null);
    }

    @Test
    public void testSaveAndGetContent() throws IOException {
        final ParseFile pf = new ParseFile(path, StandardCharsets.UTF_8);
        pf.saveContent("Hello, world!");
        assertThat("Hello, world!", is(pf.getContent()));
    }

    @Test
    public void testSaveAnyAndGetContentASCII() throws IOException {
        final ParseFile pf = new ParseFile(path, StandardCharsets.UTF_8);
        pf.saveContent("HelloА, world!Б");
        assertThat(pf.getContentASCII(), is("Hello, world!"));
        pf.saveContent("HelloА, world!Б", StandardCharsets.US_ASCII);
        assertThat(pf.getContentASCII(), is("Hello?, world!?"));
    }

    @Test
    public void testGetContent128() throws IOException {
        final ParseFile pf = new ParseFile(path, StandardCharsets.UTF_8);
        pf.saveContent("Г");
        assertThat(pf.getContent128(), is("\uFFD0ﾓ"));
    }
}