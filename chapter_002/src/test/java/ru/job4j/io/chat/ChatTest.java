package ru.job4j.io.chat;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.StringJoiner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class ChatTest {
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void start() throws IOException {
        String expected = "Привет, я бот. Пообщаемся?"
                + System.lineSeparator() + "Привет!"
                + System.lineSeparator() + "hello";

        // Create files and console args
        final File baseFile = folder.newFile("base.txt");
        Files.writeString(baseFile.toPath(), "hello");
        final File logFile = folder.newFile("log.txt");
        String[] strings = new String[]{
                baseFile.toString(),
                logFile.toString()
        };

        // Simulate user
        final InputStream in = System.in;
        final StringJoiner sj = new StringJoiner(System.lineSeparator());
        sj.add("Привет!").add("Закончить");
        InputStream stream = new ByteArrayInputStream(sj.toString().getBytes(Charset.defaultCharset()));
        System.setIn(stream);

        // Start chat
        final ArgStoreImpl argStore = new ArgStoreImpl(strings);
        final Chat chat = new Chat(argStore);
        chat.start();

        // Assert
        String out = Files.readString(logFile.toPath(), StandardCharsets.UTF_8).strip();
        System.setIn(in);
        assertThat(expected, is(out));
    }
}