package ru.job4j.io.chat;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.function.Supplier;

/**
 * Chat bot implementation.
 */
public class BotImpl implements Bot {
    /**
     * bot answers
     */
    private final List<String> answers;

    private final AnswerStrategy[] strategies;

    private AnswerStrategy currentStrategy;

    private String currentQuestion;

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
        strategies = new AnswerStrategy[] {new RandomAnswer(), new SilenceAnswer()};
        currentStrategy = strategies[0];
    }

    /**
     * @return answer
     */
    @Override
    public String answer() {
        return currentStrategy.answer();
    }

    /**
     * Update the bot behavior
     * @param question - question
     */
    @Override
    public void update(String question) {
        for (AnswerStrategy s : strategies) {
            if (s.check(question)) {
                currentStrategy = s;
                currentQuestion = question;
                break;
            }
        }
    }

    /**
     * @return ready status
     */
    @Override
    public boolean ready() {
        return !Objects.isNull(currentStrategy.answer());
    }

    /**
     * @return true if knowledge base consist answers
     */
    private boolean baseLoad() {
        return !(answers.size() == 0);
    }

    /**
     * Interface from bot answer strategy
     */
    private interface AnswerStrategy {
        boolean check(String question);
        String answer();
    }

    private static class SilenceAnswer implements AnswerStrategy {

        @Override
        public boolean check(String question) {
            return question.toLowerCase().equals("стоп");
        }

        @Override
        public String answer() {
            return null;
        }
    }

    private class RandomAnswer implements AnswerStrategy {

        @Override
        public boolean check(String question) {
            return question.toLowerCase().equals("продолжить");
        }

        @Override
        public String answer() {
            String answer = "Knowledge base don't loaded";
            if (baseLoad()) {
                final int i = new Random().nextInt(answers.size());
                answer = answers.get(i);
            }
            return answer;
        }
    }

}
