package ru.job4j.io.chat;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Chat Logger implementation
 */
public class LoggerImpl implements Logger {
    /**
     * logger writer stream
     */
    final private BufferedWriter writer;

    /**
     * @param path - path to logger resource
     */
    public LoggerImpl(Path path) {
        BufferedWriter tmp = null;
        try {
            tmp = Files.newBufferedWriter(path, StandardCharsets.UTF_8);
        } catch (NullPointerException | IOException ignored) {
        }
        this.writer = tmp;
    }

    /**
     * @param message - logged message
     */
    @Override
    public void write(String message) {
        if (valid()) {
            try {
                writer.write(message);
                writer.write(System.lineSeparator());
            } catch (IOException ignored) {
            }
        }
    }

    /**
     * @return - true if Logger can write message
     */
    @Override
    public boolean valid() {
        return writer != null;
    }

    /**
     * @throws IOException - try close
     */
    @Override
    public void close() throws IOException {
        writer.close();
    }
}
