package ru.job4j.io.chat;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import static org.junit.Assert.*;

public class ArgStoreImplTest {
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void whenLogPathExist() throws IOException {
        final File baseFile = folder.newFile("base.txt");
        final File logFile = folder.newFile("log.txt");
        String[] strings = new String[]{
                baseFile.toString(),
                logFile.toString()
        };
        final ArgStoreImpl argStore = new ArgStoreImpl(strings);
        assertTrue(argStore.logPath().toFile().isFile());
    }

    @Test
    public void whenLogPathNotExist() throws IOException {
        final File baseFile = folder.newFile("base.txt");
        final File logFile = folder.newFile("log.txt");
        String[] strings = new String[]{
                baseFile.toString(),
                logFile.getParent()
        };
        final ArgStoreImpl argStore = new ArgStoreImpl(strings);
        assertNull(argStore.logPath());
    }

    @Test
    public void whenKnowledgePathExist() throws IOException {
        final File baseFile = folder.newFile("base.txt");
        final File logFile = folder.newFile("log.txt");
        String[] strings = new String[]{
                baseFile.toString(),
                logFile.toString()
        };
        final ArgStoreImpl argStore = new ArgStoreImpl(strings);
        assertTrue(argStore.knowledgePath().toFile().isFile());
    }

    @Test
    public void whenKnowledgePathNotExist() throws IOException {
        final File baseFile = folder.newFile("base.txt");
        final File logFile = folder.newFile("log.txt");
        String[] strings = new String[]{
                baseFile.getParent(),
                logFile.toString()
        };
        final ArgStoreImpl argStore = new ArgStoreImpl(strings);
        assertNull(argStore.knowledgePath());
    }
}