package ru.job4j.io.chat;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static org.junit.Assert.*;

public class BotImplTest {
    final String msgBaseNotLoaded = "Knowledge base don't loaded";

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void whenAnswerFromBase() throws IOException {
        final File baseFile = folder.newFile("base.txt");
        Files.writeString(baseFile.toPath(), "hello");
        final BotImpl bot = new BotImpl(baseFile.toPath());
        bot.update("any question");
        assertNotEquals(bot.answer(), msgBaseNotLoaded);
    }

    @Test
    public void whenAnswerBaseNotLoaded() {
        final BotImpl bot = new BotImpl(null);
        bot.update("any answer");
        assertEquals(bot.answer(), msgBaseNotLoaded);
    }
}