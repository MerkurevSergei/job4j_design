package ru.job4j.io.concurrent;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.testng.Assert.*;

public class ConcurrentFileTest {

    private Path path;

    @BeforeSuite(alwaysRun = true)
    public void setUp() throws IOException {
        path = Files.createTempFile(null, null);
    }

    @Test
    public void testWriteAndReadString() throws IOException {
        final ConcurrentFile cFile = new ConcurrentFile(path);
        cFile.write("Hello");
        assertThat(cFile.readString(), is("Hello"));
    }

    @Test
    public void testReadAllBytes() throws IOException {
        final ConcurrentFile cFile = new ConcurrentFile(path);
        cFile.write("a");
        assertThat(cFile.readAllBytes(), is(new byte[]{97}));
    }
}