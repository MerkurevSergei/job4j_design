package ru.job4j.io.chat;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * ArgStore implementation.
 * Stored arguments from Chat application.
 */
public class ArgStoreImpl implements ArgStore {
    /**
     * store path to log
     */
    final private Path logPath;
    /**
     * store path to knowledge database
     */
    final private Path knowledgePath;

    /**
     * @param args - raw array with arguments
     */
    public ArgStoreImpl(String[] args) {
        knowledgePath = args.length > 0 && Paths.get(args[0]).toFile().isFile() ? Paths.get(args[0]) : null;
        logPath = args.length > 1 && Paths.get(args[1]).toFile().isFile() ? Paths.get(args[1]) : null;
    }

    /**
     * @return path to log
     */
    @Override
    public Path logPath() {
        return logPath;
    }

    /**
     * @return store path to knowledge database
     */
    @Override
    public Path knowledgePath() {
        return knowledgePath;
    }
}
