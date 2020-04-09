package ru.job4j.io.chat;

/**
 * Chat bot.
 */
public interface Bot {
    /**
     * @param question - question from bot
     * @return - answer
     */
    String answer(String question);
}
