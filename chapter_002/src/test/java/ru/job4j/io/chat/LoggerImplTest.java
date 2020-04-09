package ru.job4j.io.chat;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

import static org.junit.Assert.*;

public class LoggerImplTest {
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void write() throws IOException {
        final File file = folder.newFile("log.txt");
        final LoggerImpl logger = new LoggerImpl(file.toPath());
        logger.write("test");
        logger.close();
        assertEquals("test", Files.readString(file.toPath(), StandardCharsets.UTF_8).strip());
    }

    @Test
    public void close() { }
}