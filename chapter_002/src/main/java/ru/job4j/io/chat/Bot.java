package ru.job4j.io.chat;

/**
 * Chat bot.
 */
public interface Bot {
    /**
     * @return - answer
     */
    String answer();

    /**
     * Update the bot behavior
     * @param question - question
     */
    void update(String question);

    /**
     * @return ready status
     */
    boolean ready();
}
