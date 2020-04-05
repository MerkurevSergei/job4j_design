package ru.job4j.io;

import org.junit.Test;

import java.io.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class MatrixToFileTest {

    @Test
    public void write() {
        String expected = "1 2 3 4 5 6 7 8 9 " + System.lineSeparator()
                + "2 4 6 8 10 12 14 16 18 " + System.lineSeparator()
                + "3 6 9 12 15 18 21 24 27 " + System.lineSeparator()
                + "4 8 12 16 20 24 28 32 36 " + System.lineSeparator()
                + "5 10 15 20 25 30 35 40 45 " + System.lineSeparator()
                + "6 12 18 24 30 36 42 48 54 " + System.lineSeparator()
                + "7 14 21 28 35 42 49 56 63 " + System.lineSeparator()
                + "8 16 24 32 40 48 56 64 72 " + System.lineSeparator()
                + "9 18 27 36 45 54 63 72 81 " + System.lineSeparator();

        MatrixToFile matrixToFile = new MatrixToFile();
        matrixToFile.write();
        StringBuilder out = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader("exampleMatrix.txt"))) {
            while (reader.ready()) {
                out.append(reader.readLine()).append(System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertThat(expected, is(out.toString()));
    }
}