package ru.job4j.io.chat;

import java.nio.file.Path;

/**
 * Stored arguments from Chat application.
 */
public interface ArgStore {
    /**
     * @return path to log
     */
    Path logPath();

    /**
     * @return path to knowledge data base
     */
    Path knowledgePath();
}
