package ru.job4j.io;

import org.junit.Assume;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;
import java.nio.file.InvalidPathException;

import static org.junit.Assert.*;

public class ArgZipTest {
    final boolean isWindows = System.getProperty("os.name").toLowerCase().contains("windows");

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void whenValid() throws IOException {
        folder.newFile("temp.txt");
        String[] strings = new String[6];
        strings[0] = "-d";
        strings[1] = folder.getRoot().toString();
        strings[2] = "-e";
        strings[3] = "*.java";
        strings[4] = "-o";
        strings[5] = folder.newFile("temp.zip").getAbsolutePath();
        assertTrue(new ArgZip(strings).valid());
    }

    @Test
    public void whenNotValidKey() throws IOException {
        folder.newFile("temp.txt");
        String[] strings = new String[6];
        strings[0] = "-f";
        strings[1] = folder.getRoot().toString();
        strings[2] = "-e";
        strings[3] = "*.java";
        strings[4] = "-o";
        strings[5] = folder.newFile("temp.zip").getAbsolutePath();
        assertFalse(new ArgZip(strings).valid());
    }

    @Test
    public void whenExistDirectory() throws IOException {
        folder.newFile("temp.txt");
        String[] strings = new String[2];
        strings[0] = "-d";
        strings[1] = folder.getRoot().toString();
        assertFalse(new ArgZip(strings).directory().isEmpty());
    }

    @Test
    public void whenNotExistDirectory() throws IOException {
        folder.newFile("temp.txt");
        String[] strings = new String[2];
        strings[0] = "-d";
        strings[1] = folder.getRoot().toString() + File.separator + "NotExistDirectory";
        assertTrue(new ArgZip(strings).directory().isEmpty());
    }

    @Test(expected = InvalidPathException.class)
    public void whenInvalidPathDirectory() throws IOException {
        Assume.assumeTrue(isWindows);
        folder.newFile("temp.txt");
        String[] strings = new String[2];
        strings[0] = "-d";
        strings[1] = " /\\?<>*,\\|NotExistDirectory" + folder.getRoot().toString();
        assertTrue(new ArgZip(strings).directory().isEmpty());
    }

    @Test
    public void whenRightExclude() throws IOException {
        folder.newFile("temp.txt");
        String[] strings = new String[2];
        strings[0] = "-e";
        strings[1] = "*.java";
        assertFalse(new ArgZip(strings).exclude().isEmpty());
    }

    @Test
    public void whenNotRightExclude() throws IOException {
        folder.newFile("temp.txt");
        String[] strings = new String[2];
        strings[0] = "-e";
        strings[1] = ".java";
        assertTrue(new ArgZip(strings).exclude().isEmpty());
    }

    @Test
    public void whenRightOutput() throws IOException {
        folder.newFile("temp.txt");
        String[] strings = new String[2];
        strings[0] = "-o";
        strings[1] = folder.newFile("temp.zip").getAbsolutePath();
        assertFalse(new ArgZip(strings).output().isEmpty());
    }

    @Test
    public void whenNotRightOutput() throws IOException {
        folder.newFile("temp.txt");
        String[] strings = new String[2];
        strings[0] = "-o";
        strings[1] = folder.newFile("temp.zip").getParent() + File.separator + "NE" + File.separator + "1.zip";
        assertTrue(new ArgZip(strings).output().isEmpty());
    }

    @Test(expected = InvalidPathException.class)
    public void whenInvalidPathOutput() throws IOException {
        Assume.assumeTrue(isWindows);
        folder.newFile("temp.txt");
        String[] strings = new String[2];
        strings[0] = "-o";
        strings[1] = "/\\?<>*,\\|NotExistDirectory" + folder.newFile("temp.zip").getAbsolutePath();
        assertTrue(new ArgZip(strings).output().isEmpty());
    }

}