package ru.job4j.io.chat;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

/**
 * Chat bot implementation.
 */
public class BotImpl implements Bot {
    /**
     * bot answers
     */
    final List<String> answers;

    AnswerStrategy[] strategies;

    AnswerStrategy currentStrategy;
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
     * @param question - question from bot
     * @return answer
     */
    @Override
    public String answer(String question) {
        for (AnswerStrategy s : strategies) {
            if (s.check(question)) {
                currentStrategy = s;
                break;
            }
        }
        return currentStrategy.answer(question);
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
        String answer(String question);
    }

    private class SilenceAnswer implements AnswerStrategy {

        @Override
        public boolean check(String question) {
            return question.toLowerCase().equals("стоп");
        }

        @Override
        public String answer(String question) {
            return null;
        }
    }

    private class RandomAnswer implements AnswerStrategy {

        @Override
        public boolean check(String question) {
            return question.toLowerCase().equals("продолжить");
        }

        @Override
        public String answer(String question) {
            String answer = "Knowledge base don't loaded";
            if (baseLoad()) {
                final int i = new Random().nextInt(answers.size());
                answer = answers.get(i);
            }
            return answer;
        }
    }

}
