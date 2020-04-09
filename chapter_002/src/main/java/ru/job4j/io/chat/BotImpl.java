package ru.job4j.io.chat;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Chat bot implementation.
 */
public class BotImpl implements Bot {
    /**
     * bot answers
     */
    final List<String> answers;

    /**
     * @param pathBase - path to knowledge base from bot answers
     */
    public BotImpl(Path pathBase) {
        List<String> answers1 = new ArrayList<>();
        try {
            answers1 = Files.readAllLines(pathBase, StandardCharsets.UTF_8);
        } catch (NullPointerException | IOException ignored) {
        }
        answers = answers1;
    }

    /**
     * @param question - question from bot
     * @return answer
     */
    @Override
    public String answer(String question) {
        String answer = "Knowledge base don't loaded";
        if (baseLoad()) {
            final int i = new Random().nextInt(answers.size());
            answer = answers.get(i);
        }
        return answer;
    }

    /**
     * @return true if knowledge base consist answers
     */
    private boolean baseLoad() {
        return !(answers.size() == 0);
    }
}
