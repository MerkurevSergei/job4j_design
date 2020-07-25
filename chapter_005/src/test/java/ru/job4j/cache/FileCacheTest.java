package ru.job4j.cache;

import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class FileCacheTest {

    @Test
    public void putAndGet() throws IOException {
        final FileCache fileCache = new FileCache();
        fileCache.put(Path.of("Hello"), "World");
        assertEquals("World", fileCache.get(Path.of("Hello")));
    }

    @Test
    public void getWithoutFSReading() throws IOException {
        final Path tempFile = Files.createTempFile(null, null);
        Files.writeString(tempFile, "World");

        final FileCache fileCache = Mockito.spy(new FileCache());
        fileCache.get(tempFile);
        fileCache.get(tempFile);
        Mockito.verify(fileCache, Mockito.times(1)).put(tempFile, "World");
    }

    @Test(expected = IOException.class)
    public void getAndException() throws IOException {
        final FileCache fileCache = new FileCache();
        fileCache.get(Path.of("NotExist"));
        fail();
    }
}