package ru.job4j.io;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.StringJoiner;

/**
 * Example output data to file.
 */
public class MatrixToFile {
    String matrixString;

    /**
     * Initialization matrix string.
     */
    public MatrixToFile() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sb.append((i + 1) * (j + 1)).append(" ");
            }
            sb.append(System.lineSeparator());
        }
        matrixString = sb.toString();
    }

    /**
     * Write String matrixString to file.
     */
    public void write(String path) {
        try (FileOutputStream out = new FileOutputStream(path)) {
            out.write(matrixString.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
