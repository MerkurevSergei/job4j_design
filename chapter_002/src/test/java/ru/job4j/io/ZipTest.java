package ru.job4j.io;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.StringJoiner;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ZipTest {

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void whenSuccessPackFiles() throws IOException {
        final ArrayList<File> files = new ArrayList<>();
        files.add(folder.newFile("temp.txt"));
        files.add(folder.newFile("temp1.txt"));
        final Zip zip = new Zip();
        final File fileZip = folder.newFile("temp.zip");
        zip.packFiles(
                files,
                fileZip
        );

        StringJoiner s = new StringJoiner(";");
        try (final ZipInputStream zipInputStream = new ZipInputStream(new BufferedInputStream(new FileInputStream(fileZip)))) {
            ZipEntry nextEntry;
            while ((nextEntry = zipInputStream.getNextEntry()) != null) {
                s.add(Paths.get(nextEntry.getName()).getFileName().toString());
            }
        }
        assertThat("temp.txt;temp1.txt", is(s.toString()));
    }

    @Test(expected = NoSuchFileException.class)
    public void whenPackFilesNoSuchFileException() throws IOException {
        final ArrayList<File> files = new ArrayList<>();
        files.add(Paths.get(folder.toString() + "notFile.txt").toFile());
        final Zip zip = new Zip();
        final File fileZip = folder.newFile("temp.zip");
        zip.packFiles(
                files,
                fileZip
        );

        assertThat("temp.txt;temp1.txt", is(""));
    }

    @Test(expected = FileNotFoundException.class)
    public void whenPackFilesFileNotFoundException() throws IOException {
        final ArrayList<File> files = new ArrayList<>();
        final Zip zip = new Zip();
        final File fileZip = Paths.get(folder.toString() + File.separator
                + "UnknownDirectory" + File.separator + "notFile.zip").toFile();
        zip.packFiles(
                files,
                fileZip
        );

        assertThat("temp.txt;temp1.txt", is(""));
    }

    @Test
    public void whenSuccessPackSingleFile() throws IOException {
        final Zip zip = new Zip();
        final File fileZip = folder.newFile("temp.zip");
        zip.packSingleFile(
                folder.newFile("temp.txt"),
                fileZip
        );

        StringJoiner s = new StringJoiner(";");
        try (final ZipInputStream zipInputStream = new ZipInputStream(new BufferedInputStream(new FileInputStream(fileZip)))) {
            ZipEntry nextEntry;
            while ((nextEntry = zipInputStream.getNextEntry()) != null) {
                s.add(Paths.get(nextEntry.getName()).getFileName().toString());
            }
        }
        assertThat("temp.txt", is(s.toString()));
    }

    @Test
    public void whenSuccessPackFromArgZip() throws IOException {
        File tempFolder = folder.newFolder("temp");
        folder.newFile("temp" + File.separator + "temp.txt");
        folder.newFile("temp" + File.separator + "temp1.txt1");
        folder.newFile("temp" + File.separator + "temp2.txt");
        File fileZip = folder.newFile("temp.zip");

        String[] args = new String[6];
        args[0] = "-d";
        args[1] = tempFolder.getAbsolutePath();
        args[2] = "-e";
        args[3] = "*.txt1";
        args[4] = "-o";
        args[5] = fileZip.getAbsolutePath();

        final Zip zip = new Zip();

        zip.packFromArgZip(new ArgZip(args));

        StringJoiner s = new StringJoiner(";");
        try (final ZipInputStream zipInputStream = new ZipInputStream(new BufferedInputStream(new FileInputStream(fileZip)))) {
            ZipEntry nextEntry;
            while ((nextEntry = zipInputStream.getNextEntry()) != null) {
                s.add(Paths.get(nextEntry.getName()).getFileName().toString());
            }
        }
        assertThat("temp.txt;temp2.txt", is(s.toString()));
    }
}
